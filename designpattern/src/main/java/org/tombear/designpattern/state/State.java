package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public interface State {

    void insertCoin();

    void ejectCoin();

    void turnsCrank();

    void dispenseBall();
}
