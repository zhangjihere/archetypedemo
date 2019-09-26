package org.tombear.designpattern.visitor;

/**
 * Created by ji.zhang on 9/26/19.
 */
public class Item implements Element {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
