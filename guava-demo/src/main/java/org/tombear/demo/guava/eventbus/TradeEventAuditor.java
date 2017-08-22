package org.tombear.demo.guava.eventbus;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;

/**
 * <P>订阅了事件，被事件驱动，处理事件</P>
 *
 * @author tombear on 2017-08-21 20:59.
 */
public class TradeEventAuditor {

    private List<TradeEvent> tradeEvents = Lists.newArrayList();

    public TradeEventAuditor(EventBus eventBus) {
        eventBus.register(this);
    }

    /**
     * 订阅了事件，处理事件
     * @param event 接收订阅的事件
     */
    @Subscribe
    public void auditTrade(TradeEvent event) {
        tradeEvents.add(event);
        System.out.println("put a event: " + event.toString());
    }

    public List<TradeEvent> getTradeEvents() {
        return tradeEvents;
    }
}
