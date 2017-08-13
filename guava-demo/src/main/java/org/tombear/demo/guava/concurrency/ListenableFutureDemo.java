package org.tombear.demo.guava.concurrency;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * <P>There is a small limitation to the ListenableFuture. addListener method approach; we have no
 * access to the returned object, and we can't specify different methods to run for success or
 * failure conditions. Fortunately, we have an option that gives us that ability. ->FutureCallbackDemo</P>
 *
 * @author tombear on 2017-08-13 10:50.
 */
public class ListenableFutureDemo {
    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

    }
}
