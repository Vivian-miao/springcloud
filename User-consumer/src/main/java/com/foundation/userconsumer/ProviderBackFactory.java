package com.foundation.userconsumer;

import com.foundation.userapi.Person;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 使用FallbackFactory检查具体错误
 * 2021-06-29 10:39:30
 *
 * @author zhh
 */
@Component
public class ProviderBackFactory implements FallbackFactory<ConsumerApi> {
    @Override
    public ConsumerApi create(Throwable throwable) {
        return new ConsumerApi() {
            @Override
            public Map<Integer, String> getMap(Integer id) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap2(Integer id, String name) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap3(Map<String, Object> map) {
                return null;
            }

            @Override
            public Map<Integer, String> postMap(Map<String, Object> map) {
                return null;
            }

            @Override
            public String alive() {
                System.out.println(throwable.getLocalizedMessage());
                throwable.printStackTrace();
                if (throwable instanceof FeignException.InternalServerError) {
                    return "远程服务器500" + throwable.getMessage();
                } else if (throwable instanceof RuntimeException) {

                    return "请求时异常：" + throwable;
                } else {
                    return "no problem";
                }
            }

            @Override
            public String getById(Integer id) {
                return null;
            }

            @Override
            public Person postPerson(Person person) {
                return null;
            }
        };
    }
}
