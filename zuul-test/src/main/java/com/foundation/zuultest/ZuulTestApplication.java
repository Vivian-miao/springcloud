package com.foundation.zuultest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zhh
 * @date 2021-06-29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulTestApplication.class, args);
    }

}
