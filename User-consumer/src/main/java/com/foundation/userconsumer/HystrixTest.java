package com.foundation.userconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * hystrix独立使用脱离spring cloud
 * 2021-06-25 14:41:25
 *
 * @author zhh
 */
public class HystrixTest extends HystrixCommand<Object> {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    public static void main(String[] args) {
        // HystrixTest hystrixTest = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext"));

        // execute()：以同步阻塞方式执行run()。调用execute()后，hystrix先创建一个新线程运行run()，
        // 接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
        // System.out.println("result:" + hystrixTest.execute());

        // queue()：以异步非阻塞方式执行run()。
        // 一调用queue()就直接返回一个Future对象，同时hystrix创建一个新线程运行run(), 调用程序通过Future.get()拿到run()的返回结果，而Future.get()是阻塞执行的
        Future<Object> futureResult = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = "";
        try {
            result = (String) futureResult.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("程序结果：" + result);
    }

    /**
     * try里的逻辑
     */
    @Override
    protected Object run() {
        System.out.println("逻辑执行");
        // int i = 1/1，不会抛异常，return ok；如果int i= 1/0，调用getFallback方法，return getFeedback
        int i = 1 / 1;
        return "ok";
    }

    /**
     * catch里的逻辑
     */
    @Override
    protected Object getFallback() {
        return "getFallback";
    }
}
