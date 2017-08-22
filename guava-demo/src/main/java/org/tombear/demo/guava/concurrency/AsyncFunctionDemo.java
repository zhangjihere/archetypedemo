package org.tombear.demo.guava.concurrency;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

/**
 * <P>异步的Function，多任务结果的异步转换，不阻塞</P>
 * <li>也演示了SettableFuture的示例</li>
 * <li>但是注意，在最终转换后的ListenableFunction结果上调用get还是会等待</li>
 *
 * @author tombear on 2017-08-13 12:18.
 */
public class AsyncFunctionDemo implements AsyncFunction<Long, String> {

    private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
    private ListeningExecutorService service = null;

    public AsyncFunctionDemo(ListeningExecutorService service) {
        this.service = service;
        map.put(123L, "我在这个map里");
        map.put(456L, "我也在这个map里");
    }

    /**
     * 针对不同的条件
     *
     * @return SettableFuture或者ListenableFuture
     */
    @Override
    public ListenableFuture<String> apply(Long input) throws Exception {
        if (map.containsKey(input)) {
            SettableFuture<String> settableFuture = SettableFuture.create();
            settableFuture.set(map.get(input));
            Thread.sleep(5000);
            return settableFuture;
        } else {
            ListenableFuture<String> listenableFuture = service.submit(new StringCallable(input));
            return listenableFuture;
        }
    }

    public static void main(String[] args) throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        AsyncFunctionDemo asyncFunction = new AsyncFunctionDemo(service);

        ListenableFuture<String> listenableFuture1 = asyncFunction.apply(123L);
        String rst1 = listenableFuture1.get();
        System.out.println("result1: " + rst1);
        ListenableFuture<String> listenableFuture2 = asyncFunction.apply(456L);
        String rst2 = listenableFuture2.get();
        System.out.println("result2: " + rst2);
        ListenableFuture<String> listenableFuture3 = asyncFunction.apply(789L);
        String rst3 = listenableFuture3.get();
        System.out.println("result3: " + rst3);

    }

    /**
     * 新的任务
     */
    private class StringCallable implements Callable<String> {
        private final Long input;

        public StringCallable(Long input) {
            this.input = input;
        }

        @Override
        public String call() throws Exception {
            String retrieved = "获取最终结果的process";
            map.putIfAbsent(input, retrieved);
            return retrieved;
        }
    }
}
