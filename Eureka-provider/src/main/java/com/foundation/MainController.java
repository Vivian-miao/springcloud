package com.foundation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * 2021-03-29 15:23:07
 *
 * @author zhh
 */
@RestController
public class MainController {

    @Value("${server.port}")
    String port;


    @Autowired
    HealthStatusService healthStatusSrv;

    @GetMapping("/getHi")
    public String getHi() {
        System.out.println(port);
        return "hi,port is " + port;
    }

    @GetMapping("/getMap")
    public Map<String, String> getMap() {
        return Collections.singletonMap("id", "100");
    }

    @GetMapping("/getPerson")
    public Person getPerson() {
        return new Person(1, "Jerry");
    }

    @GetMapping("/getPersonByParam")
    public Person getPersonByParam(String name) {
        return new Person(1, name);
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }

    @PostMapping("/postLocation")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception {
        URI uri = new URI("https://www.baidu.com/s?wd="+person.getName().trim());
        // 不加会报空指针异常
        response.addHeader("Location", uri.toString());
        return uri;
    }
}
