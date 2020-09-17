package com.springcloud.base.webautomation;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author Administrator
 *
 */
public class HttpClientPool {

	private static Logger logger = LoggerFactory.getLogger(HttpClientPool.class);

	PoolingHttpClientConnectionManager cm = null;

	private static class HttpClientPoolHolder {
		private static HttpClientPool _instance = new HttpClientPool();
	}

	public static HttpClientPool getInstance() {
		return HttpClientPoolHolder._instance;
	}

	/**
	 * 绕过验证
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	private HttpClientPool() {
		try {
			SSLContext sslcontext = createIgnoreVerifySSL();
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("https", new SSLConnectionSocketFactory(sslcontext))
					.register("http", new PlainConnectionSocketFactory()).build();
			cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(200);
			cm.setDefaultMaxPerRoute(20);
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			logger.info("HttpClientPool.error:{}", e.getMessage());
		}
		Runtime.getRuntime().addShutdownHook(new Thread(cm::close));
	}


	public CloseableHttpClient getHttpClient(HttpInterceptor... interceptors) {
		return getHttpClient(Lists.newArrayList(interceptors));
	}


	public CloseableHttpClient getHttpClient(List<HttpInterceptor> interceptors) {
		if (interceptors == null || interceptors.isEmpty()) {
			return HttpClients.custom().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setRedirectStrategy(new LaxRedirectStrategy()).setConnectionManager(cm).setDefaultCookieStore(new BasicCookieStore()).build();
		}
		HttpClientBuilder builder = HttpClients.custom().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setRedirectStrategy(new LaxRedirectStrategy()).setDefaultCookieStore(new BasicCookieStore()).setConnectionManager(cm);
		interceptors.parallelStream().filter(Objects::nonNull).sorted((x, y) -> (x.getSeq() <= y.getSeq() ? 1 : -1))
				.forEach(interceptor -> {
					builder.addInterceptorFirst((HttpRequestInterceptor) interceptor)
							.addInterceptorLast((HttpResponseInterceptor) interceptor);
				});
		CloseableHttpClient client = builder.build();
		return client;
	}


	public String invoke(HttpUriRequest request, HttpInterceptor... interceptors) {
		StopWatch sw = new StopWatch();
		sw.start();
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		InputStream in = null;
		try {
			HttpContext context = new BasicHttpContext();
			context.setAttribute("sw", sw);
			client = this.getHttpClient(interceptors);
			response = client.execute(request, context);
			in = response.getEntity().getContent();
			return IOUtils.toString(in);
		} catch (Exception e) {
			logger.error("发送Http请求出现异常！", e);
		}
		// 使用finally块来关闭输入流
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("发送Http请求出现异常！", e);
				}
			} 
		}
		return null;
	}

	/**
	 * 执行http请求，携带证书
	 * 
	 * @param request
	 * @param certFileIs
	 *            证书文件
	 * @param certKey
	 *            证书秘钥
	 * @param interceptors
	 * @return
	 */
	public String invokeSSL(HttpUriRequest request, InputStream certFileIs, String certKey, HttpInterceptor... interceptors) {
		StopWatch sw = new StopWatch();
		sw.start();
		CloseableHttpResponse response = null;
		InputStream in = null;
		try {
			HttpContext context = new BasicHttpContext();
			context.setAttribute("sw", sw);
			CloseableHttpClient client = this.getHttpClientSSL(certFileIs, certKey, interceptors);
			response = client.execute(request, context);
			in = response.getEntity().getContent();
			return IOUtils.toString(in);
		} catch (Exception e) {
			logger.error("发送Http请求出现异常！", e);
		}
		// 使用finally块来关闭输入流
		finally {
			IOUtils.closeQuietly(in);
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("发送Http请求出现异常！", e);
				}
			}
		}
		return null;
	}


	public CloseableHttpClient getHttpClientSSL(InputStream certFileIs, String certKey, HttpInterceptor... interceptors)
			throws Exception {
		return getHttpClientSSL(certFileIs, certKey, Lists.newArrayList(interceptors));
	}

	private CloseableHttpClient getHttpClientSSL(InputStream certFileIs, String certKey, List<HttpInterceptor> interceptors)
			throws Exception {

		// 参数校验
		if (null == certFileIs || !StringUtils.isEmpty(certKey)) {
			logger.error("发送HttpSSL请求出现异常！证书为空或秘钥为空！！！");
			throw new RuntimeException("发送HttpSSL请求出现异常！证书为空或秘钥为空！！！");
		}

		// 指定读取证书格式为PKCS12
		KeyStore keyStore = KeyStore.getInstance("PKCS12");


			// 指定PKCS12的密码
        try {
            keyStore.load(certFileIs, certKey.toCharArray());
        } finally {
            certFileIs.close();
        }

        // 指定TLS版本
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certKey.toCharArray()).build();
		// 设置httpclient的SSLSocketFactory
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		HttpClientBuilder httpClientBuilder = HttpClients.custom().setSSLSocketFactory(sslsf);

		if (interceptors != null && !interceptors.isEmpty()) {
			interceptors.stream().filter(Objects::nonNull).sorted((x, y) -> (x.getSeq() <= y.getSeq() ? 1 : -1))
					.forEach(interceptor -> {
						httpClientBuilder.addInterceptorFirst((HttpRequestInterceptor) interceptor)
								.addInterceptorLast((HttpResponseInterceptor) interceptor);
					});
		}

		return httpClientBuilder.build();
	}

}
