package org.tombear.demo.guava.concurrency;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <P>并发工具，频率限制器，即限制每秒钟多少线程访问</P>
 * 用法相似于Semaphore
 *
 * @author tombear on 2017-08-13 12:20.
 */
public class RateLimiterDemo {

    private static ThreadFactory tf = new ThreadFactoryBuilder().setDaemon(false).build();
    private static ExecutorService service = Executors.newFixedThreadPool(2, tf);

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5.5, 5, TimeUnit.MINUTES);
        RateLimiter limiter = RateLimiter.create(4.0);

        //block, wait for blocking
        limiter.acquire();
        service.submit(() -> System.out.println("I am working."));
        //tryAcquire and then choose to do
        if (limiter.tryAcquire()) {
            System.out.println("Alice is doing something.");
        } else {
            System.out.println("Boo can't get in");
        }
    }

}
