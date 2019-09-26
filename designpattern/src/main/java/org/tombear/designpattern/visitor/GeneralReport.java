package org.tombear.designpattern.visitor;

/**
 * Created by ji.zhang on 9/26/19.
 */
public class GeneralReport implements Visitor {

    private int customerNo = 0;
    private int orderNo = 0;
    private int itemNo = 0;

    @Override
    public void visit(Customer customer) {
        System.out.println(customer.getName());
        customerNo++;
    }

    @Override
    public void visit(Order order) {
        System.out.println(order.getName());
        orderNo++;
    }

    @Override
    public void visit(Item item) {
        System.out.println(item.getName());
        itemNo++;
    }

    void displayResult() {
        System.out.println("Number of customers: " + customerNo);
        System.out.println("Number of orders: " + orderNo);
        System.out.println("Number of items: " + itemNo);
    }
}
