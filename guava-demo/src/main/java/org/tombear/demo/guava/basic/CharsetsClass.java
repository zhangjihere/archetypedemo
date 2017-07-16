package org.tombear.demo.guava.basic;

import com.google.common.base.Charsets;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class CharsetsClass {

    @Test
    public void chersetTest(){
        //控制Java的String.getBytes指定字符集默认抛出异常的冗余
        byte[] bytes2 = "foobarbaz".getBytes(Charsets.UTF_8);
        System.out.println(Arrays.toString(bytes2));
    }
}
