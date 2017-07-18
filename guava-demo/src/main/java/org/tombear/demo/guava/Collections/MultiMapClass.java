package org.tombear.demo.guava.Collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class MultiMapClass {

    @Test
    public void arrayListMultimapMethod() {
        ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();

        int numExcpectedKeys = 3;
        int numExpectedValuesPerKey = 2;
        ArrayListMultimap<String, String> multiMap2 = ArrayListMultimap.create(numExcpectedKeys, numExpectedValuesPerKey);

        ArrayListMultimap<String, String> mulitMap3 = ArrayListMultimap.create(multiMap2);

        ArrayListMultimap<String, String> multiMap4 = ArrayListMultimap.create();
        multiMap4.put("Foo", "1");
        multiMap4.put("Foo", "2");
        multiMap4.put("Foo", "3");
        List<String> expected = Lists.newArrayList("1", "2", "3");
        System.out.println(multiMap4);
        Assert.assertEquals(multiMap4.get("Foo"), expected);//通过

        //某一个key对应的ArrayList有重复放置
        ArrayListMultimap<String, String> multiMap5 = ArrayListMultimap.create();
        multiMap5.put("Bar", "1");
        multiMap5.put("Bar", "2");
        multiMap5.put("Bar", "3");
        multiMap5.put("Bar", "3");
        multiMap5.put("Bar", "3");
        List<String> expected2 = Lists.newArrayList("1", "2", "3", "3", "3");//通过
        Assert.assertEquals(multiMap5.get("Bar"), expected2);

        multiMap5.put("Foo", "1");
        multiMap5.put("Foo", "2");
        multiMap5.put("Foo", "3");
        multiMap5.put("Bar", "1");
        multiMap5.put("Bar", "2");
        multiMap5.put("Bar", "3");
        System.out.println("multiMap5 size: " + multiMap5.size());

        Map<String, Collection<String>> map = multiMap5.asMap();
        System.out.println(map);
    }

    @Test
    public void hashMultimapMethod() {
        HashMultimap<String, String> multiMap = HashMultimap.create();
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        System.out.println("multiMap size: " + multiMap.size());
    }

}
