package com.foundation.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 2021-06-29 11:19:45
 *
 * @author zhh
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestService {

    private final RestTemplate restTemplate;

    /**
     * HystrixCommand(defaultFallback = "resolve")：如果back出问题，调用resolve方法
     *
     * @return string
     */
    @HystrixCommand(defaultFallback = "resolve")
    public String back() {
        String url = "http://provider/back";
        return restTemplate.getForObject(url, String.class);
    }

    public String resolve() {
        return "降级resolve";
    }
}
