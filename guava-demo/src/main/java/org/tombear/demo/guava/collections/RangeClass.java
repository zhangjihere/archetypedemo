package org.tombear.demo.guava.collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;

import org.junit.Test;
import org.tombear.demo.guava.Person;

import java.util.Collection;
import java.util.List;

/**
 * Created by ji.zhang on 7/24/17.
 */
public class RangeClass {

    static final Integer[] intList = {3, 5, 7};
    static final Double[] doubleList = {3.0, 5.5, 9.9};

    @Test
    public void rangeMethod() {
        Range<Integer> numberRange1 = Range.closed(1, 10);
        //both return true meaning inclusive
        numberRange1.contains(10);
        numberRange1.contains(1);
        Range<Integer> numberRange2 = Range.open(1, 10);
        //both return false meaning exclusive
        numberRange2.contains(10);
        //几个例子
        Range<Integer> range = Range.openClosed(0, 10);//左开右闭
        Range.downTo(11.0, BoundType.CLOSED);//下边界
        Range.lessThan(9.1);//严格小于
        Range<Long> all = Range.all();//long的所有值
        Range<Integer> integerRange = Range.encloseAll(Lists.asList(1, intList));//包含所有可迭代元素的最小集合，整型
        System.out.println("integerRange: " + integerRange);//[1‥7]
        Range<Integer> canonical = integerRange.canonical(DiscreteDomain.integers());//标准化，为整型域或长整型域
        System.out.println("cannonical Range: " + canonical);//[1‥8)
        Range<Double> doubleRange = Range.encloseAll(Lists.asList(1D, doubleList));
        System.out.println("doubleRange: " + doubleRange);//[1.0‥9.9]
    }

    /**
     * 有效的过滤器
     * 利用Range实现了Predicat接口，组合Function接口实现，制作一个有效的过滤器
     */
    @Test
    public void rangePredicate() {
        Range<Integer> ageRange = Range.closed(30, 50);
        Function<Person, Integer> ageFunction = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };
        Predicate<Person> agePred = Predicates.compose(ageRange, ageFunction);
        System.out.println("age filter range: " + agePred.toString());

        List<Person> personList = Person.createPersonList();
        System.out.println("before filter: " + personList);
        Collection<Person> filter = Collections2.filter(personList, agePred);
        System.out.println("after filter: " + filter);
    }

    /**
     * 范围集合的Map映射，及结合映射的差集
     */
    @Test
    public void rangeRangeMapDemo() {
        RangeMap<Integer, String> rangeMap = new ImmutableRangeMap.Builder<Integer, String>()
                .put(Range.closed(1, 7), "aaa")// {[1,7]}
                .put(Range.openClosed(10, 13), "bbb")// {[1,7], (7,13]}
                .put(Range.closedOpen(15, 20), "ccc").build();// {[1,7], (7,13], [15,20)}
        System.out.println(rangeMap.get(3));
        System.out.println(rangeMap.subRangeMap(Range.closedOpen(4, 16)));

        RangeMap<Integer, String> treeRangeMap = TreeRangeMap.create();
        treeRangeMap.put(Range.closedOpen(1, 10), "first");
    }
}
