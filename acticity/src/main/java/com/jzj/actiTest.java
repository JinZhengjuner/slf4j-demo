package com.jzj;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jzj.activity.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class actiTest {
    private AtomicInteger integer = new AtomicInteger(0);
    @Test
    public void test(){
        boolean sss = MyStringUtils.isEmpty("sss");
        System.out.println(sss);

    }

    private void m1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 11
                , 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1)
                , new ThreadFactoryBuilder().setNameFormat("jinzhengjun-%d").build()
                , (r, e) -> {
            if (!e.isShutdown()) {
                log.info("拒绝策略执行了");
                r.run();
            }
        });

        for (int i = 0; i < 10; i++) {
            CompletableFuture.supplyAsync(() -> {
                try {
                    log.info(""+integer.incrementAndGet());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            },executor);
        }
        executor.shutdown();
    }
}
