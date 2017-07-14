package org.tombear.demo.guava.basic;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class StringsClass {

    @Test
    public void stringPad() {
        String rlt = Strings.padEnd("foo", 6, 'x');
        System.out.println(rlt);
        rlt = Strings.padStart("foo", 6, 'x');
        System.out.println(rlt);
        rlt = Strings.emptyToNull("");
        System.out.println(rlt);
    }
}
