package com.foundation.userconsumer;

import com.foundation.userapi.Person;
import com.foundation.userapi.UserApi;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2021-04-27 16:51:01
 *
 * @author zhh
 */
@RestController
public class UserController implements UserApi {
    @Value("${server.port}")
    String port;

    /**
     * 根据id获取数据
     *
     * @param id id
     * @return String
     */
    @Override
    public String getById(Integer id) {
        System.out.println(id);
        return id.toString();
    }

    private final AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {
        try {
            System.out.println("准备睡");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = count.getAndIncrement();
        System.out.println(port + "====好的，第" + i + "次调用");
        // 制造异常
        // int i1 = 1 / 0;
        return "port:" + port;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        System.out.println(id);
        return Collections.singletonMap(id, "meme");
    }

    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id, String name) {
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @Override
    public Person postPerson(@RequestBody Person person) {
        System.out.println(ToStringBuilder.reflectionToString(person));
        return person;
    }
}
