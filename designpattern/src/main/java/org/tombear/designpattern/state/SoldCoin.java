package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class SoldCoin implements State {

    private BallMechine mechine;

    SoldCoin(BallMechine mechine) {
        this.mechine = mechine;
    }

    @Override
    public void insertCoin() {
        System.out.println("  insertCoin -> Sorry, you have inserted ONE coin.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("  ejectCoin -> Sorry, you have handled the crank.");
    }

    @Override
    public void turnsCrank() {
        System.out.println("  turnsCrank -> Sorry, you have handled the crank.");
    }

    @Override
    public void dispenseBall() {
        System.out.println("  dispenseBall -> OK");
        mechine.releaseBall();
        if (mechine.ballNum > 0) {
            mechine.changeState(mechine.noCoin);
        } else {
            System.out.println("Oops, out of balls.");
            mechine.changeState(mechine.outOfBall);
        }
    }
}
