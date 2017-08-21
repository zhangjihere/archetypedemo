package org.tombear.demo.guava.cache;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.MapMaker;

import org.junit.Test;
import org.tombear.demo.guava.TradeAccount;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ji.zhang on 8/21/17.
 */
public class CacheClass {

    @Test
    public void cacheDemo() throws ExecutionException {
        //MapMaker
        ConcurrentMap<Object, Object> mapMaker = new MapMaker().concurrencyLevel(2)
                .weakValues()
                .makeMap();
        //CacheLoader
//        CacheLoader<Key,value> cacheLoader = CacheLoader.from(Function<Key,Value> func);
//        CacheLoader<Object,Value> cacheLoader = CacheLoader.from(Supplier<Value> supplier);
        TACacheLoader cacheLoader = new TACacheLoader();
        //Cache Demo
        LoadingCache<String, TradeAccount> cache = CacheBuilder.newBuilder()
                .recordStats()                                    //记录缓存使用的一些统计数据，注意有性能损失
                .expireAfterWrite(5L, TimeUnit.MINUTES)   //缓存中某一个entry被写入后的过期时间，删除
                .expireAfterAccess(20L, TimeUnit.MINUTES) //缓存中某一个entry被访问后的过期时间，删除
                .refreshAfterWrite(5L, TimeUnit.MINUTES)  //某一entry被写入后，超过过期时间，刷新这个entry
                .ticker(Ticker.systemTicker())
                .maximumSize(5000L)
                .softValues()
                .removalListener(TARemovalListener.getAsyncListener())
                .build(cacheLoader);//在请求缓存key对应的value时，如果没有对应value则执行CacheLoader的方法
        cache.get("ta");
        //show cache stats
        CacheStats stats = cache.stats();
        System.out.println("cache stats: " + stats);
        //CacheBuilderSpec，字符串构建缓存对象
        String configString = "concurrencyLevel=10,refreshAfterWrite=5s";
        String spec = "concurrencyLevel=10,expireAfterAccess=5m,softValues";
        CacheBuilderSpec cacheBuilderSpec2 = CacheBuilderSpec.parse(spec);
        CacheBuilder.from(cacheBuilderSpec2)
                .ticker(Ticker.systemTicker())
                .removalListener(new TARemovalListener())
                .build(cacheLoader);//在请求缓存key对应的value时，如果没有对应value则执行CacheLoader的方法
    }

    static class TARemovalListener implements RemovalListener<String, TradeAccount> {
        @Override
        public void onRemoval(RemovalNotification<String, TradeAccount> notification) {
            switch (notification.getCause()) {
                case COLLECTED:
                    break;
                case EXPIRED:
                    break;
                case EXPLICIT:
                    break;
                case REPLACED:
                    break;
                case SIZE:
                    break;
                default:
            }
            System.out.println("entry is removed.");
        }

        /**
         * 异步的缓存entry移除监听器
         */
        public static RemovalListener<String, TradeAccount> getAsyncListener() {
            TARemovalListener tarl = new TARemovalListener();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            return RemovalListeners.asynchronous(tarl, executorService);
        }

    }

    static class TACacheLoader extends CacheLoader<String, TradeAccount> {
        @Override
        public TradeAccount load(String key) throws Exception {
            TradeAccount.Builder builder = new TradeAccount.Builder();
            return builder.setId("1").setBalance(2.2).setOwner("steven").build();
        }
    }

}
