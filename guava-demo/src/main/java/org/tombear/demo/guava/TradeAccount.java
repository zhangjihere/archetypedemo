package org.tombear.demo.guava;

import com.google.common.base.MoreObjects;

/**
 * Created by ji.zhang on 8/21/17.
 */
public class TradeAccount {
    private String id;
    private String owner;
    private double balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("owner", owner)
                .add("balance", balance)
                .toString();
    }

    public static class Builder {
        private TradeAccount ta;

        public Builder() {
            ta = new TradeAccount();
        }

        public TradeAccount build() {
            return ta;
        }

        public Builder setId(String id) {
            ta.setId(id);
            return this;
        }

        public Builder setOwner(String owner) {
            ta.setOwner(owner);
            return this;
        }

        public Builder setBalance(double balance) {
            ta.setBalance(balance);
            return this;
        }
    }
}
