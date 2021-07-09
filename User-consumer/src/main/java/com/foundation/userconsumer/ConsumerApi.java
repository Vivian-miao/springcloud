package com.foundation.userconsumer;

import com.foundation.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 不结合eureka，就是自定义一个client名字。就用url属性指定，服务器列表：url="http://ip:port/"
 * 没有代码侵入，方便做异构系统
 * 2021-04-27 16:58:20
 *
 * @author zhh
 *
 * <p>
 * fallback = ProviderBack.class
 * fallbackFactory = ProviderBackFactory.class
 */
@FeignClient(name = "provider", fallbackFactory = ProviderBackFactory.class)
public interface ConsumerApi extends UserApi {
    /**
     * getMap
     * 这里的@GetMapping和@RequestParam，是给Feign看的，provider/getMap?id={1}
     *
     * @param id id
     * @return map
     */
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    /**
     * getMap2
     *
     * @param id   id
     * @param name name
     * @return
     */
    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name);

    /**
     * getMap3
     *
     * @param map map
     * @return map
     */
    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    /**
     * postMap
     *
     * @param map map
     * @return map
     */
    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);
}
