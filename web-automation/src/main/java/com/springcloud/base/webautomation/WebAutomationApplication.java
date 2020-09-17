package com.springcloud.base.webautomation;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebAutomationApplication {

    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientPool.getInstance().getHttpClient(new LoginInterceptor());
        try {
            CloseableHttpResponse response = client.execute(request());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求成功呢");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        new SpringApplicationBuilder(WebAutomationApplication.class).web(WebApplicationType.NONE).run(args);
        System.out.println(" start ...");
    }



    public static HttpUriRequest request(){
        HttpPost httpPost = new HttpPost("http://localhost:8080/loginByMobile.do");
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        //参数
        nameValuePairs.add(new BasicNameValuePair("mobile", "18665574844"));
        nameValuePairs.add(new BasicNameValuePair("validCode", "888888"));
        nameValuePairs.add(new BasicNameValuePair("isOpenImage", ""));
        nameValuePairs.add(new BasicNameValuePair("ImgValidCode", ""));

        httpPost.setEntity(new UrlEncodedFormEntity((nameValuePairs), Charset.forName("gbk")));

        return httpPost;
    }
}
