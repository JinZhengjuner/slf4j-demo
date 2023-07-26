package com.jzj.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TranceIdUtils {

    /**
     * SimpleDateFormat 对象
     */
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 执行总次数
     */
    private static final int EXECUTE_COUNT = 1000;

    /**
     * 同时运行的线程数量
     */
    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    SDF.parse("2022-06-07");
                } catch (InterruptedException e) {
                    System.out.println("获取信号量出错");
                    e.printStackTrace();
                    System.exit(1);
                } catch (ParseException e) {
                    System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                }
                semaphore.release();
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");

    }

}
