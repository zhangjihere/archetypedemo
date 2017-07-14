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
        String stringWithLinebreaks = "HHH\naaa \n";
        String rlt = CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLinebreaks, ' ');
        System.out.println(rlt);

        String tabsAndSpaces = "aa    qq  \n  fef";
        rlt = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' ');
        System.out.println(rlt);

        String beginTabsAndSpaces = "    aa    \nqq    fef";
        rlt = CharMatcher.WHITESPACE.trimAndCollapseFrom(   beginTabsAndSpaces, ' ');
        System.out.println(rlt);

        //retain
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);
        assertThat(expected,is(retained));

        String noSpaceAndDigit = "abcdefghijklmn";
        expected = "989234";
        retained = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE).retainFrom(noSpaceAndDigit);
        assertThat(expected,is(retained));
    }
}
