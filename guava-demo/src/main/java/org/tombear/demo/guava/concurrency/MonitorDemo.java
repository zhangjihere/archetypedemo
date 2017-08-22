package org.tombear.demo.guava.concurrency;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-13 10:53.
 */
public class MonitorDemo {
    private List<String> list = new ArrayList<String>();
    private static final int MAX_SIZE = 10;

    private Monitor monitor = new Monitor();
    private Monitor.Guard condition = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    /**
     * 检查Monitor的状态
     */
    public void addToList(String item) throws InterruptedException {
        //enterWhen
        monitor.enterWhen(condition);
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
        //enterIf
        if (monitor.enterIf(condition)) {
            try {
                System.out.println("I am ocuppying monitor and do something");
            } finally {
                monitor.leave();
            }
        }
//        monitor.enter();
//        monitor.tryEnter();
//        monitor.tryEnterIf(condition);
    }

}
