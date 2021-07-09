package com.foundation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * restful http
 * 2021-04-27 14:37:37
 *
 * @author zhh
 */
@RestController
public class RestfulController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client10")
    public Object client10() {
        String url = "http://Foam:80/getHi";
        ResponseEntity<String> entity = new RestTemplate().getForEntity(url, String.class);
        System.out.println("entity = " + entity);
        return new RestTemplate().getForObject(url, String.class);
    }

    @GetMapping("/client11")
    public Object client11() {
        String url = "http://Foam:80/getMap";
        ResponseEntity<String> entity = new RestTemplate().getForEntity(url, String.class);
        System.out.println("entity = " + entity);
        return new RestTemplate().getForObject(url, String.class);
    }

    @GetMapping("/client12")
    public Object client12() {
        String url = "http://Foam:80/getPerson";
        ResponseEntity<Person> entity = new RestTemplate().getForEntity(url, Person.class);
        System.out.println("entity = " + entity);
        return new RestTemplate().getForObject(url, Person.class);
    }

    @GetMapping("/client13")
    public Object client13() {
        String url = "http://Foam:80/getPersonByParam?name={1}";
        return new RestTemplate().getForObject(url, Person.class, "Lucy");
    }

    @GetMapping("/client14")
    public Object client14() {
        String url = "http://Foam:80/getPersonByParam?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "Tony");
        return new RestTemplate().getForObject(url, Person.class, map);
    }

    @GetMapping("/client15")
    public Object client15(HttpServletResponse response) throws Exception {
        String url = "http://Foam:80/postLocation";
        Map<String, String> map = Collections.singletonMap("name", "Tony");
        URI location = new RestTemplate().postForLocation(url, map, Person.class);
        assert location != null;
        response.sendRedirect(location.toURL().toString());
        return null;
    }
}
