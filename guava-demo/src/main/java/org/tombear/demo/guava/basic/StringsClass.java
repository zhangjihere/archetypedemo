package org.tombear.demo.guava.basic;

import com.google.common.base.Strings;

import com.sun.source.tree.AssertTree;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import jdk.nashorn.internal.AssertsEnabled;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class StringsClass {

    @Test
    public void stringsMethod() {
        // 扩充字符串的末尾
        String rlt = Strings.padEnd("foo", 6, 'x');
        System.out.println(rlt);
        // 扩充字符串的开头
        rlt = Strings.padStart("foo", 6, 'x');
        System.out.println(rlt);
        // 转换空字符串到null
        rlt = Strings.emptyToNull("");
        System.out.println(rlt);
        // 转换null为空字符串
        rlt = Strings.nullToEmpty(null);
        System.out.println(rlt);
        // 两字符串共同的前缀
        rlt = Strings.commonPrefix("foobar", "fogleaf");
        System.out.println(rlt);
        // 判断字符串为空字符串后者null
        boolean rltB = Strings.isNullOrEmpty("");
        Assert.assertThat(true, Is.is(rltB));
        System.out.println(rltB);
    }
}
