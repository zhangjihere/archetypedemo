package org.tombear.demo.guava.concurrency;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;

import org.tombear.demo.guava.Person;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * <P>使用Futures把ListenableFuture的结果通过Future.tranformAsync转换为异步的</P>
 * <li>最终的ListenableFuture的get方法仍然是要阻塞的</li>
 *
 * @author tombear on 2017-08-13 12:19.
 */
public class FuturesDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> lfuture = service.submit(new IntegerCallable());
        AsyncFunction<Integer, Person> asyncFunction = new IntegerPersonAsyncFunction();
        //任务处理到获得结果，被转换为异步执行，任务的结果被传递给AsyncFunction实例来转换处理
        ListenableFuture<Person> lf = Futures.transformAsync(lfuture, asyncFunction, Executors.newSingleThreadExecutor());
        //AsyncFunction结果在这里会等待
        System.out.println(lf.get().toString());
    }

    /**
     * 任务
     */
    private static class IntegerCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(5000);
            return 25;
        }
    }

    /**
     * 结果的AsyncFunction
     */
    private static class IntegerPersonAsyncFunction implements AsyncFunction<Integer, Person> {
        @Override
        public ListenableFuture<Person> apply(Integer age) throws Exception {
            List<Person> personList = Person.createPersonList();
            Optional<Person> personOptional = FluentIterable.from(personList).firstMatch(new Predicate<Person>() {
                @Override
                public boolean apply(Person person) {
                    return age.equals(person.getAge());
                }
            });
            SettableFuture<Person> future = SettableFuture.create();
            future.set(personOptional.get());
            return future;
        }
    }
}
