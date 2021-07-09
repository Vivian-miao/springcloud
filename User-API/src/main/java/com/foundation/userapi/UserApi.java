package com.foundation.userapi;

import org.springframework.web.bind.annotation.*;

/**
 * 2021-04-29
 *
 * @author zhh
 */
//@RequestMapping("/User")
public interface UserApi {
    /**
     * alive方法
     * @return
     */
    @GetMapping("/User/alive")
    String alive();

    /**
     * 根据id获取数据
     * @param id id
     * @return String
     */
    @GetMapping("/User/getById")
    String getById(@RequestParam("id") Integer id);

    /**
     * post person
     * @param person
     * @return
     */
    @PostMapping("/User/postPerson")
    Person postPerson(@RequestBody Person person);
}
