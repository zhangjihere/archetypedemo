package org.tombear.designpattern.visitor;

/**
 * Created by ji.zhang on 9/26/19.
 */
public class Client {

    public static void main(String[] args) {
        Customer cust_1 = new Customer("Cust_1");
        cust_1.addOrder(new Order("order_01", "apple"));
        cust_1.addOrder(new Order("order_02", "pear"));
        cust_1.addOrder(new Order("order_03", "orange"));

        Customer cust_2 = new Customer("Cust_2");
        Order order_04 = new Order("order_04");
        order_04.addItem(new Item("item_01"));
        order_04.addItem(new Item("item_02"));
        order_04.addItem(new Item("item_03"));
        cust_2.addOrder(order_04);

        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.addCustomer(cust_1);
        customerGroup.addCustomer(cust_2);

        GeneralReport visitor = new GeneralReport();
        customerGroup.accept(visitor);
        visitor.displayResult();

    }
}
