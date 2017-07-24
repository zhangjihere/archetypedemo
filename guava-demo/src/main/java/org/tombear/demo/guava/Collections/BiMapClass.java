package org.tombear.demo.guava.Collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ji.zhang on 7/24/17.
 */
public class BiMapClass {

    @Test
    public void biMapPut() {
        HashBiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "Tom");
        biMap.put(2, "bear");
        biMap.put(3, "Tom");
    }

    /**
     * 强制放入已经存在的Value的K-V，挤出旧的
     */
    @Test
    public void biMapForcePut() {
        HashBiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "Tom");
        biMap.put(2, "bear");
        biMap.forcePut(3, "Tom");
        System.out.println(biMap.toString());
    }

    /**
     * 反转BiMap
     */
    @Test
    public void biMapInverse() {
        HashBiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "Tom");
        biMap.put(2, "bear");

        Assert.assertThat(biMap.get(1), Is.is("Tom"));
        Assert.assertThat(biMap.get(2), Is.is("bear"));

        BiMap<String, Integer> inverseBiMap = biMap.inverse();
        Assert.assertThat(inverseBiMap.get("Tom"), Is.is(1));
        Assert.assertThat(inverseBiMap.get("bear"), Is.is(2));
    }
}
