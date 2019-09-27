package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class NoCoin implements State {

    private BallMechine mechine;

    NoCoin(BallMechine mechine) {
        this.mechine = mechine;
    }

    @Override
    public void insertCoin() {
        System.out.println("  insertCoin -> OK.");
        mechine.changeState(mechine.hasCoin);
    }

    @Override
    public void ejectCoin() {
        System.out.println("  ejectCoin -> Sorry, has not ONE coin.");
    }

    @Override
    public void turnsCrank() {
        System.out.println("  turnsCrank -> Sorry, you should insertCoin.");
    }

    @Override
    public void dispenseBall() {
        System.out.println("  dispenseBall -> Sorry, you should insertCoin first.");
    }
}
