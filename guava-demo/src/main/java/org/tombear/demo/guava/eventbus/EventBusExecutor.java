package org.tombear.demo.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * <P>模拟业务处理，后发布事件</P>
 *
 * @author tombear on 2017-08-21 20:41.
 */
public class EventBusExecutor {

    private EventBus eventBus = null;

    public EventBusExecutor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * mock publish event
     */
    public void publishEvent() {
        TradeEvent taEvent = process();
        eventBus.post(taEvent);
    }

    /**
     * mock business process
     *
     * @return result
     */
    private TradeEvent process() {
        return new TradeEvent(new Random().nextDouble(), GregorianCalendar.getInstance().getTime());
    }

    /**
     * 演示的主方法
     */
    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new EventBus("DemoEventBus");
        TradeEventAuditor tradeEventAuditor = new TradeEventAuditor(eventBus);
        EventBusExecutor eventBusExecutor = new EventBusExecutor(eventBus);
        DeadEventSubscriber deadEventSubscriber = new DeadEventSubscriber(eventBus);
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            eventBusExecutor.publishEvent();
        }
        System.out.println(tradeEventAuditor.getTradeEvents());
        eventBus.unregister(tradeEventAuditor);

        //异步事件总线，适用于订阅者处理事件是很重量的情况下
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newSingleThreadExecutor());
    }
}
