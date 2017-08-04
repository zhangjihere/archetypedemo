package org.tombear.demo.guava.Collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.Table;

import org.junit.Test;

/**
 * 不可变集合类的demo
 * Created by ji.zhang on 8/4/17.
 */
public class ImmutableClass {

    @Test
    public void ImmutableMethod() {
        Multimap<Integer, String> map = new ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "Foo")
                .putAll(2, "Foo", "Bar", "Baz")
                .putAll(4, "Huey", "Duey", "Luey")
                .put(3, "Single").build();

        RangeMap<Integer, String> rangeMap = new ImmutableRangeMap.Builder<Integer, String>()
                .put(Range.closedOpen(1, 5), "first span")
                .build();

        Table<Integer, Integer, String> table = new ImmutableTable.Builder<Integer, Integer, String>()
                .put(1, 1, "first cell")
                .build();

        BiMap<String, Integer> build = new ImmutableBiMap.Builder<String, Integer>()
                .put("first", 1)
                .put("second", 2)
                .build();

    }
}
