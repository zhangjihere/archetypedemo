package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class BallMechine {

    State outOfBall = new OutOfBall(this);
    State noCoin = new NoCoin(this);
    State hasCoin = new HasCoin(this);
    State soldBall = new SoldCoin(this);

    int ballNum;
    State currentState;

    public BallMechine(int ballNum) {
        this.ballNum = ballNum;
        if (ballNum > 0) {
            this.currentState = noCoin;
        } else {
            this.currentState = outOfBall;
        }
    }

    void changeState(State state) {
        currentState = state;
        System.out.println("State change to " + state.getClass().getSimpleName());
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void turnsCrank() {
        currentState.turnsCrank();
        currentState.dispenseBall();
    }

    public void releaseBall() {
        System.out.println("=> A ball is out!");
        if (this.ballNum > 0) {
            this.ballNum--;
        }
    }

}
