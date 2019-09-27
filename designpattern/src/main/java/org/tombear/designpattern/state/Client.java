package org.tombear.designpattern.state;

/**
 * Created by ji.zhang on 9/27/19.
 */
public class Client {
    public static void main(String[] args) {
        BallMechine mechine = new BallMechine(2);

        mechine.insertCoin();
        mechine.ejectCoin();
        mechine.insertCoin();
        mechine.turnsCrank();

        mechine.ejectCoin();
        mechine.insertCoin();
        mechine.insertCoin();
        mechine.turnsCrank();

        mechine.insertCoin();
        mechine.turnsCrank();
        mechine.ejectCoin();
    }
}
