package com.jzj;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogbackTest {

    private static final Logger log = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Integer> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println("name: " + Thread.currentThread().getName());
                countDownLatch.countDown();
                list.add(finalI);
            });
        }
        countDownLatch.await();
        System.out.println(list);

    }
    @Test
    public void testQuick(){
        for (int i = 0; i < 10000; i++) {
            log.error("error");
            log.warn("warn");
            log.info("info");
            log.debug("debug");//默认级别
            log.trace("trace");
        }

    }
}
