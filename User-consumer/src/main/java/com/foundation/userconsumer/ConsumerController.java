package com.foundation.userconsumer;

import com.foundation.userapi.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 2021-04-27 17:14:32
 *
 * @author zhh
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumerController {

    private final ConsumerApi consumerApi;
    private final RestService restService;

    /**
     * try{
     *     1、发起向服务方的请求
     *      1.1 判断连接超时，这次请求，记录到服务里
     *        http请求，线程消耗
     *          // 隔离
     *          map(URI,线程数) 或 线程池(线程数)
     *          if(当前线程满了){throw exception}
     *          // 熔断
     *          设置阈值，计数count++，连续失败次数达到阈值
     *          请求/不请求/半请求：if(new Random == 1) 或者 按时间 发请求
     *          if(count == 阈值){throw exception}
     *      1.2 尝试向其他服务器发起请求
     *     2、还是没成功
     * }catch(Exception e) {
     *     // 降级
     *     1、return 避免返回不友好的信号
     *     2、return 返回另外一个东西到MQ里，延后处理
     * }
     *
     * Hystrix实现了超时机制和断路器模式
     * @return string
     */
    @GetMapping("/alive")
    public String alive() {
        return consumerApi.alive();
    }

    @GetMapping("/getById")
    public String getById(Integer id) {
        return consumerApi.getById(id);
    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return consumerApi.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id, String name) {
        System.out.println(id + "=" + name);
        return consumerApi.getMap2(id, name);
    }

    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return consumerApi.getMap3(map);
    }

    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return consumerApi.postMap(map);
    }

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String, Object> map) {
        Person person = new Person();
        person.setId(Integer.valueOf(map.get("id").toString()));
        person.setName(map.get("name").toString());
        return consumerApi.postPerson(person);
    }

    /**
     * 给springmvc看的，变成servlet
     * @return String
     */
    @GetMapping("/back")
    @HystrixCommand(defaultFallback = "resolve")
    public String back() {
        return restService.back();
    }

    public String resolve() {
        return "降级resolve";
    }
}
