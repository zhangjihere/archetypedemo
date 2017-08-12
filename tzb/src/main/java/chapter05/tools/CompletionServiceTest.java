package chapter05.tools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {

	public static void main(String []args) throws InterruptedException, ExecutionException {
		final Random random = new Random();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CompletionService<String>completionService = new ExecutorCompletionService<String>(executorService);
		for(int i = 0 ; i < 100 ; i++) {
			final int num = i;
			//ExecutorCompletionService.submit是可以sumbit完成所有任务（Callable）之后再方便获取Future对象
			completionService.submit(new Callable<String>() {
				public String call() {
					try {
						Thread.sleep((random.nextLong()) & 5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "a" + num;
				}
			});
			//直接执行任务Runnable
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((random.nextLong()) & 5000);
						System.out.println("run empty");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			//ExecutorService.submit任务（Callable）立即返回Future来使用
            Future<String> normalSubmit = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try {
                        Thread.sleep((random.nextLong()) & 5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "b" + num;
                }
            });
        }
        /**
         * 特别留意，同一个ExecutorService实例提交的任务，与使用其实例化的CompletionService实例提交的任务，相互独立。
         * 即两者共用的线程池，CompletionService随后由take或poll的任务结果与ExecutorService的execute或submit的任务结果无关
         */
		for(int i = 0 ; i < 100 ; i++) {
			Future<String> f = completionService.take();
			System.out.println(f.get());
		}

        executorService.shutdown();
	}
}
