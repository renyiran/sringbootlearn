package com.anthony.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用controller
 *
 * @Author: renyiran
 * @Date: 2019/6/6 9:44
 */
@RestController
@RequestMapping("/async")
public class AsyncControllerTest {


    @GetMapping("/test1")
    public DeferredResult<String> test1() throws InterruptedException {
        DeferredResult<String> result = new DeferredResult<>(1000L, "TimeOut!!");
        result.onTimeout(() -> System.out.println(Thread.currentThread().getName() + "----" + "调用超时"));
        result.onCompletion(() -> System.out.println(Thread.currentThread().getName() + "----" + "调用完成"));
        new Thread(new AsynTask(result)).start();
        return result;
    }


    private static class AsynTask implements Runnable {
        private DeferredResult result;

        private AsynTask(DeferredResult result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5L);
                result.setResult(UUID.randomUUID().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
