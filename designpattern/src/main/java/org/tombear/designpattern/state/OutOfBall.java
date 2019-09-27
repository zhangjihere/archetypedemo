package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class OutOfBall implements State {

    private BallMechine mechine;

    OutOfBall(BallMechine mechine) {
        this.mechine = mechine;
    }

    @Override
    public void insertCoin() {
        System.out.println("  insertCoin -> Sorry, balls are sold out.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  ejectCoin -> Sorry, balls are sold out.");
    }

    @Override
    public void turnsCrank() {
        System.out.println("  turnsCrank -> Sorry, balls are sold out.");
    }

    @Override
    public void dispenseBall() {
        System.out.println("  dispenseBall -> Sorry, balls are sold out.");
    }
}
