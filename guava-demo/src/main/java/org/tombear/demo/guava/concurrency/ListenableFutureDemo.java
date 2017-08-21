package org.tombear.demo.guava.concurrency;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * <P>There is a small limitation to the ListenableFuture. addListener method approach; we have no
 * access to the returned object, and we can't specify different methods to run for success or
 * failure conditions. Fortunately, we have an option that gives us that ability.
 * ->FutureCallbackDemo</P>
 *
 * @author tombear on 2017-08-13 10:50.
 */
public class ListenableFutureDemo {

    /**
     * 这个回调的演示是无法货值主任务是成功还是失败以及其返回值的
     */
    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<String> listenableFuture = executorService.submit(new MainTask());
        listenableFuture.addListener(new SimpleCallback(), executorService);
    }

    /**
     * 主任务
     */
    private static class MainTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "我是主任务";
        }
    }

    /**
     * 完成主任务的回调
     */
    private static class SimpleCallback implements Runnable {
        @Override
        public void run() {
            System.out.println("listeningFuture返回了结果之后的回调方法");
        }

    }
}
