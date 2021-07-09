package com.foundation;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 拦截器
 * 2021-04-27 16:17:48
 *
 * @author zhh
 */
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("拦截啦！！！");
        System.out.println(httpRequest.getURI());

        ClientHttpResponse response = execution.execute(httpRequest, body);

        System.out.println(response.getHeaders());
        return response;
    }
}
