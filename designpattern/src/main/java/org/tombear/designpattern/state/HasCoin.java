package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class HasCoin implements State {

    private BallMechine mechine;

    HasCoin(BallMechine mechine) {
        this.mechine = mechine;
    }

    @Override
    public void insertCoin() {
        System.out.println("  insertCoin -> mechine has ONE coin.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  ejectCoin -> OK");
        mechine.changeState(mechine.noCoin);
    }

    @Override
    public void turnsCrank() {
        System.out.println("  turnsCrank -> OK");
        mechine.changeState(mechine.soldBall);
    }

    @Override
    public void dispenseBall() {
        System.out.println("  dispenseBall -> Sorry, crank hasn't been handled.");
    }
}
