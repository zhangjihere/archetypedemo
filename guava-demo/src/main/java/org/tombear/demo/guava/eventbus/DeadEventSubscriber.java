package org.tombear.demo.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * <P>没有订阅者的事件，自动包装成为DeadEvent</P>
 *
 * @author tombear on 2017-08-21 21:34.
 */
public class DeadEventSubscriber {

    public DeadEventSubscriber(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleUnsubscribedEvent(DeadEvent deadEvent) {
        System.out.println("No subscribers for " + deadEvent.getEvent());
    }
}
