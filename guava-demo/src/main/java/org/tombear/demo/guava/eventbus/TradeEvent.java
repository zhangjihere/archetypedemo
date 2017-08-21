package org.tombear.demo.guava.eventbus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.Date;

/**
 * <P>定义一个事件例子</P>
 *
 * @author tombear on 2017-08-21 20:43.
 */
public class TradeEvent {
    private double amount;
    private Date tradeExecutionTime;

    public TradeEvent(double amount, Date tradeExecutionTime) {
        Preconditions.checkArgument(amount > 0.0, "amount not > 0.0, wrong");
        Preconditions.checkNotNull(tradeExecutionTime, "tradeExecutionTime is null");
        this.amount = amount;
        this.tradeExecutionTime = tradeExecutionTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTradeExecutionTime() {
        return tradeExecutionTime;
    }

    public void setTradeExecutionTime(Date tradeExecutionTime) {
        this.tradeExecutionTime = tradeExecutionTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("amount", amount)
                .add("tradeExecutionTime", tradeExecutionTime)
                .toString();
    }
}
