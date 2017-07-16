package org.tombear.demo.guava.basic;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.*;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class PreconditionExample {
    private String label;
    private int[] values = new int[5];
    private int currentIndex;

    /**
     * 封装空指针异常的状态判断
     */
    public PreconditionExample(String label) {
        this.label = checkNotNull(label, "Label can''t be null");
    }

    /**
     * 封装索引越界异常的预状态判断
     * 封装参数异常的状态判断
     */
    public void updateCurrentIndexValue(int index, int valueToSet) {
        this.currentIndex = checkElementIndex(index, values.length, "Index out of bounds for values");
        checkArgument(valueToSet <= 100, "Value can't be more than 100");
        values[this.currentIndex] = valueToSet;
    }

    /**
     * 封装状态异常的判断
     */
    public void doOperation() {
        checkState(validateObjectState(), "Can't perform operation");
    }

    private boolean validateObjectState() {
        return this.label.equalsIgnoreCase("open") && values[this.currentIndex] == 10;
    }
}
