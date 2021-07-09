package com.foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 启动类上添加此注解标识该服务为配置中心
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudApplication.class, args);
    }

}
