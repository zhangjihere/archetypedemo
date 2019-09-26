package org.tombear.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ji.zhang on 9/26/19.
 */
public class Customer implements Element {

    private String name;
    private List<Order> orders = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }

    void addOrder(Order order) {
        orders.add(order);
    }
}
