package org.tombear.demo.guava.oddsends;

import com.google.common.base.Throwables;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 23:24.
 */
public class ThrowablesClass {

    @Test
    public void chainThrowablesDmeo() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<Throwable> throwables = null;

        Callable<FileInputStream> fileCallable = new Callable<FileInputStream>() {
            @Override
            public FileInputStream call() throws Exception {
                return new FileInputStream("Bogus file");
            }
        };

        Future<FileInputStream> fisFuture = executor.submit(fileCallable);
        try {
            fisFuture.get();
        } catch (Exception e) {
            throwables = Throwables.getCausalChain(e);
        }
        Assert.assertThat(throwables.get(0).getClass().isAssignableFrom(ExecutionException.class), Is.is(true));
        Assert.assertThat(throwables.get(1).getClass().isAssignableFrom(FileNotFoundException.class), Is.is(true));
        executor.shutdownNow();
    }

    @Test
    public void rootCauseThrowable() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Throwable cause = null;
        final String nullString = null;

        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return nullString.substring(0, 2);
            }
        };

        Future<String> stringFuture = executor.submit(stringCallable);
        try {
            stringFuture.get();
        } catch (Exception e) {
            cause = Throwables.getRootCause(e);
        }
        Assert.assertThat(cause.getClass().isAssignableFrom(NullPointerException.class), Is.is(true));
        executor.shutdownNow();
    }
}
