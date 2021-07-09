package com.foundation;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 2021-03-29 15:23:07
 *
 * @author zhh
 */
@RestController
public class MainController {

    /** 抽象的 */
    @Autowired
    DiscoveryClient client;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/getHi")
    public String getHi() {
        return "hi";
    }

    @GetMapping("/client")
    public String client() {
        client.getServices().forEach(System.out::println);
        return "hi";
    }

    @GetMapping("/client2")
    public Object client2() {
        return client.getInstances("provider");
    }

    @GetMapping("/client3")
    public String client3() {
        client.getInstances("provider").forEach(System.out::println);
        return "client3";
    }

    @GetMapping("/client4")
    public String client4() {
        // 具体服务
        // List<InstanceInfo> list = eurekaClient.getInstancesById("Foam:provider:80");
        // 使用服务名找列表
        List<InstanceInfo> list = eurekaClient.getInstancesByVipAddress("provider", false);
        if (list.size() > 0) {
            InstanceInfo info = list.get(0);
            if (info.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + info.getHostName() + ":" + info.getPort() + "/getHi";
                System.out.println("url = " + url);
                String object = new RestTemplate().getForObject(url, String.class);
                System.out.println(object);
            }
        }
        return "client4";
    }

    @GetMapping("/client5")
    public Object client5() {
        // ribbon完成客户端的负载均衡，过滤了down的节点
        ServiceInstance instance = loadBalancerClient.choose("provider");
        String url = instance.getUri() + "/getHi";
        System.out.println("url = " + url);
        System.out.println(new RestTemplate().getForObject(url, String.class));
        return "client5";
    }
}
