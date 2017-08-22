package org.tombear.demo.guava.concurrency;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * <P>FutureCallback的demo，happen-before的例子</P>
 *
 * @author tombear on 2017-08-13 11:54.
 */
public class FutureCallbackDemo {
    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));
        MainTask mainTask = new MainTask();
        FutureCallbackImpl callback = new FutureCallbackImpl();

        ListenableFuture<String> lstnFuture = service.submit(mainTask);
        Futures.addCallback(lstnFuture, callback);//这个方法happen-before对于回调（callback）执行，执行线程为caller线程
        Futures.addCallback(lstnFuture, callback, service);//同上，这个方法happen-before对于回调（callback）执行，但callback执行线程为executorService负责

        System.out.println("hah" + callback.getCallbackResult());//主线程直接通过，此时可能任务没有完成也没有执行回调方法
        //Assuming success, would return "Task completed successfully"
    }

    /**
     * 主任务
     */
    private static class MainTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Task completed";
        }
    }

    /**
     * FutureCallback的实现
     */
    private static class FutureCallbackImpl implements FutureCallback<String> {
        private StringBuilder builder = new StringBuilder();

        @Override
        public void onSuccess(String result) {
            builder.append(result).append(" successfully");
        }

        @Override
        public void onFailure(Throwable t) {
            builder.append(t.toString());
        }

        public String getCallbackResult() {
            return builder.toString();
        }
    }
}
