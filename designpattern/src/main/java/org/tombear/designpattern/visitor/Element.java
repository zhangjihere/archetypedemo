package org.tombear.designpattern.visitor;

/**
 * Created by ji.zhang on 9/26/19.
 */
public interface Element {
    void accept(Visitor visitor);
}
