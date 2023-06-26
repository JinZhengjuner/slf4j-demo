package com.jzj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class A {
    private String name;
    private String address;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long allStart = System.currentTimeMillis();
        List<CompletableFuture<Integer>> completableFutureList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map(x -> {
                    return CompletableFuture.supplyAsync(() ->
                            {
                                Integer anInt = null;
                                try {
                                    anInt = getInt();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return anInt;
                            }
                            , executorService);
                }).collect(Collectors.toList());

        completableFutureList.parallelStream().forEach(x -> {
            try {
                Integer integer = x.get(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });

        System.out.println("all耗时：" + (System.currentTimeMillis() - allStart));
        executorService.shutdown();

    }

    private static Integer getInt() throws InterruptedException {
        int i = new Random().nextInt(10);
        Thread.sleep(i*1000);
        System.out.println("档次的i=" + i +  "-----------name" + Thread.currentThread().getName());
        return i;
    }

}
