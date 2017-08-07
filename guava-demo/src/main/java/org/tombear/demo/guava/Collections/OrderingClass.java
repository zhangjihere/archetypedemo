package org.tombear.demo.guava.Collections;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import org.junit.Test;
import org.tombear.demo.guava.City;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * about guava Ordering class
 * Created by ji.zhang on 8/7/17.
 */
public class OrderingClass {

    /**
     * defind a comparator to sort City by population in min to max.
     */
    static class CityPopOrdering implements Comparator<City> {
        @Override
        public int compare(City c1, City c2) {
            return Ints.compare(c1.getPopulation(), c2.getPopulation());
        }
    }

    /**
     * 注意，Ordering实现了接口Comparator,
     * 如果不使用Ordering.from(..).nullsFirst()或Ordering.from(..).nullsLast获取Ordering实例
     * 则很可能在使用Ordering过程中抛出NPE。
     */
    @Test
    public void OrderingReverseMethod() {
        Map<String, City> cityMapData = City.createCityMapData();
        List<City> cities = Lists.newArrayList(cityMapData.values());
        System.out.println("origin: " + cities.toString());

        CityPopOrdering cityPopOrdering = new CityPopOrdering();

        //获取反向的比较器
        Comparator<City> reversed = Ordering.from(cityPopOrdering).reversed();
        //获取反向的Ordering
        Ordering<City> reverse = Ordering.from(cityPopOrdering).reverse();

        //获取最大的或者最小值的几个元素集合
        List<City> grt2 = Ordering.from(cityPopOrdering).greatestOf(cities, 2);
        System.out.println("greatestOf 2: " + grt2.toString());
        List<City> lst2 = Ordering.from(cityPopOrdering).leastOf(cities, 2);
        System.out.println("leastOf 2: " + lst2.toString());

        //针对要排序的集合首先执行onResultOf传入的Function，然后的在对结果施以排序
        //先调用元素toString，然后比较结果的按照字符串大小写
        Ordering<Object> resultOrdering = Ordering
                .from(String.CASE_INSENSITIVE_ORDER)
                .onResultOf(Functions.toStringFunction());
        System.out.println("resultOf: " + resultOrdering.max(cities));

        //带排序集合有null
        //获取为null元素都排序到集合前边部的Ordering
        cityMapData.put("nullCity", null);
        cityMapData.put("emptyCity", null);
        Ordering<City> cityOrderingNulls = Ordering.from(cityPopOrdering).nullsFirst();
        List<City> citiesWithNull = Lists.newArrayList(cityMapData.values());
        citiesWithNull.sort(cityOrderingNulls);
        System.out.println("null at first part: " + citiesWithNull.toString());
        //获取最大的人口的城市,不会因city为null而抛出异常,但是null会被认为是最大或者最小值
        System.out.println("max: " + cityOrderingNulls.max(citiesWithNull));


    }
}
