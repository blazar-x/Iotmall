package com.liang.iotmall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Mr.Liang
 * @date: 2021/5/27 16:22
 * @Version: 1.0
 */
public class ThreadTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");
//        CompletableFuture.runAsync(()->{
//            System.out.println("当前线程："+Thread.currentThread().getId());
//            int i=10/2;
//            System.out.println("运行结果:"+i);
//        },executor);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果:" + i);
            return i;
        }, executor).whenComplete((i,InterruptedException)->{
            System.out.println("任务完成，结果是:"+i+"异常是"+InterruptedException);
        }).exceptionally(throwable -> {
//            可以感知异常，并返回默认值
            return 10;
        });
        Integer integer = future.get();
        System.out.println("main...end"+integer);
    }
}
