package org.tombear.designpattern.visitor;

/**
 * Created by ji.zhang on 9/26/19.
 */
public interface Visitor {

    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}
