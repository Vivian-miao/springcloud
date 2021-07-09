package com.foundation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡
 * 2021-04-26 15:08:45
 *
 * @author zhh
 */
@RestController
public class LoadBalanceController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/client6")
    public String client6() {
        ServiceInstance instance = loadBalancerClient.choose("provider");
        String url = instance.getUri() + "/getHi";
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 手动负载均衡
     *
     * @return string
     */
    @GetMapping("/client7")
    public String client7() {
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        // 轮询算法
//        int andIncrement = new AtomicInteger().getAndIncrement();
//        ServiceInstance instance = instances.get(andIncrement % instances.size());
        // 随机算法
        int nextInt = new Random().nextInt(instances.size());
        ServiceInstance instance = instances.get(nextInt);
        String url = instance.getUri() + "/getHi";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/client8")
    public Object client8() {
        // ribbon完成客户端的负载均衡，过滤了down的节点
        ServiceInstance instance = loadBalancerClient.choose("provider");
        String url = instance.getUri() + "/getHi";
        return new RestTemplate().getForObject(url, String.class);
    }

    @GetMapping("/client9")
    public Object client9() {
        // 自动处理URL
        String url ="http://Foam:80/getHi";
        return new RestTemplate().getForObject(url, String.class);
    }
}
