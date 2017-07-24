package org.tombear.demo.guava.Collections;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

/**
 * Created by ji.zhang on 7/24/17.
 */
public class TableClass {

    /**
     * HashBasedTable stores data in Map<R, Map<C, V>>
     */
    @Test
    public void tableMethod() {
        HashBasedTable<Integer, Integer, String> table1 = HashBasedTable.create();
        HashBasedTable<Integer, Integer, String> table2 = HashBasedTable.create(2, 2);
        HashBasedTable<Integer, Integer, String> table3 = HashBasedTable.create(table2);

        //插入测试数据
        table3.put(1, 1, "r1c1");
        table3.put(1, 2, "r1c2");
        table3.put(2, 1, "r2c1");
        table3.put(2, 2, "r2c2");
        table3.put(3, 1, "r3c1");
        table3.put(3, 2, "r3c2");
        System.out.println("orgin table=> " + table3);
        //测试清除元素
        System.out.println("(1,1) contained: " + table3.contains(1, 1));
        table3.remove(1, 1);
        System.out.println("after remove (1,1): " + table3.contains(1, 1));
        System.out.println("(1,1) element value: " + table3.get(1, 1));
        //单取列和行
        System.out.println("column 2: " + table3.column(2));
        System.out.println("row 1: " + table3.row(1));
        //打印所有元素
        System.out.println("final table: " + table3);
    }
}
