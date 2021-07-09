package com.foundation.userconsumer;

import com.foundation.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 2021-06-28 14:45:58
 *
 * @author zhh
 */
@Component
public class ProviderBack implements ConsumerApi{


    /**
     * alive方法
     */
    @Override
    public String alive() {
        return "降级";
    }

    /**
     * 根据id获取数据
     *
     * @param id id
     * @return String
     */
    @Override
    public String getById(Integer id) {
        return null;
    }

    /**
     * post person
     *
     * @param person
     * @return
     */
    @Override
    public Person postPerson(Person person) {
        return null;
    }

    /**
     * getMap
     * 这里的@GetMapping和@RequestParam，是给Feign看的，provider/getMap?id={1}
     *
     * @param id id
     * @return map
     */
    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    /**
     * getMap2
     *
     * @param id   id
     * @param name name
     * @return
     */
    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    /**
     * getMap3
     *
     * @param map map
     * @return map
     */
    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    /**
     * postMap
     *
     * @param map map
     * @return map
     */
    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }
}
