package org.tombear.demo.guava.collections;

import com.google.common.collect.Sets;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class SetsClass {

    @Test
    public void differenceMethod() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");
        Sets.SetView<String> difference = Sets.difference(s1, s2);
        System.out.println(difference);

    }

    @Test
    public void symmetricDifferenceMethod() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");
        Sets.SetView<String> sets = Sets.symmetricDifference(s1, s2);
        System.out.println(sets);
    }

    @Test
    public void intersectionMethod() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("3", "2", "4");
        Sets.SetView<String> sv = Sets.intersection(s1, s2);
        Assert.assertThat(sv.size() == 2
                        && sv.contains("2")
                        && sv.contains("3")
                , Is.is(true));
    }

    @Test
    public void unionMethod() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("3", "2", "4");
        Sets.SetView<String> sv = Sets.union(s1, s2);
        Assert.assertThat(sv.size() == 4
                        && sv.contains("2")
                        && sv.contains("3")
                        && sv.contains("4")
                        && sv.contains("1")
                , Is.is(true));
    }

}
