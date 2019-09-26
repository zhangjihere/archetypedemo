package org.tombear.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ji.zhang on 9/26/19.
 */
public class CustomerGroup implements Element {

    private List<Customer> customerList = new ArrayList<>();

    @Override
    public void accept(Visitor visitor) {
        for (Customer customer : customerList) {
            customer.accept(visitor);
        }
    }

    void addCustomer(Customer customer) {
        customerList.add(customer);
    }
}
