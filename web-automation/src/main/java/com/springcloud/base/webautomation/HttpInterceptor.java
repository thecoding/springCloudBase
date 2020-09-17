package com.springcloud.base.webautomation;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;

/**
 * @desc HttpInterceptor
 * @author Administrator
 *
 */
public interface HttpInterceptor extends HttpRequestInterceptor, HttpResponseInterceptor {
	 
	default public int getSeq() {
		return 0;
	}
}