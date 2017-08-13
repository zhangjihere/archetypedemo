package org.tombear.demo.guava.basic;

import com.google.common.base.CharMatcher;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class CharMatcherClass {

    @Test
    public void cmTest() {
        //替换换行符，合并行
        String stringWithLinebreaks = "HHH\naaa \n";
        String rlt = CharMatcher.breakingWhitespace().replaceFrom(stringWithLinebreaks, ' ');
        System.out.println(rlt);

        //替换多空白
        String tabsAndSpaces = "aa    qq  \n  fef";
        rlt = CharMatcher.whitespace().collapseFrom(tabsAndSpaces, ' ');
        System.out.println(rlt);

        //去取行首空白，替换多空白
        String beginTabsAndSpaces = "    aa    \nqq    fef";
        rlt = CharMatcher.whitespace().trimAndCollapseFrom(beginTabsAndSpaces, ' ');
        System.out.println(rlt);

        //retain，保留指定数字字符集合
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.javaDigit().retainFrom(lettersAndNumbers);
        assertThat(expected, is(retained));

        //保留多字符集合
        String noSpaceAndDigit = "abcdefghijklmn";
        expected = "989234";
        retained = CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom(noSpaceAndDigit);
        assertThat(expected, is(retained));
    }
}
