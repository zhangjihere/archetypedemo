package org.tombear.demo.guava.Collections;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

/**
 * 可迭代实例的操作
 * Created by ji.zhang on 7/18/17.
 */
public class FluentIterableClass {
    static class Person {
        String firstName;
        String lastName;
        int age;
        String sex;

        Person(String firstName, String lastName, int age, String sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.sex = sex;
        }
    }

    List<Person> personList;
    Person person1;
    Person person2;
    Person person3;
    Person person4;

    @Before
    public void setUp() {
        person1 = new Person("Wilma", "Flintstone", 30, "F");
        person2 = new Person("Fred", "Flintstone", 32, "M");
        person3 = new Person("Betty", "Rubble", 31, "F");
        person4 = new Person("Barney", "Rubble", 33, "M");
        personList = Lists.newArrayList(person1, person2, person3, person4);
    }

    // 过滤List集合元素
    @Test
    public void testFilter() throws Exception {
        Iterable<Person> filteredByAge =
                FluentIterable.from(personList).filter(new Predicate<Person>() {
                    @Override
                    public boolean apply(Person input) {
                        return input.age > 31;
                    }
                });
//        以上可替换为如下JavaAPI的lambda表达方法
//        Iterable<Person> filteredByAge = personList.stream()
//                .filter(input -> input.age > 31)
//                .collect(Collectors.toList());
        Assert.assertThat(Iterables.contains(filteredByAge, person2), is(true));
        Assert.assertThat(Iterables.contains(filteredByAge, person4), is(true));
        Assert.assertThat(Iterables.contains(filteredByAge, person1), is(false));
        Assert.assertThat(Iterables.contains(filteredByAge, person3), is(false));
    }

    // 转换List集合到List
    @Test
    public void testTransformToList() throws Exception {
        List<String> transToList =
                FluentIterable.from(personList).transform(new Function<Person, String>() {
                    @Override
                    public String apply(Person input) {
                        return Joiner.on('#').join(input.lastName, input.firstName, input.age);
                    }
                }).toList();
//        以上可替换为如下JavaAPI的lambda表达方法
//        List<String> transToList = personList.stream()
//                        .map(input -> Joiner.on('#').join(input.lastName, input.firstName, input.age))
//                        .collect(Collectors.toList());
        Assert.assertThat(transToList.get(1), is("Flintstone#Fred#32"));
    }

    // 转换List集合映射到Map
    @Test
    public void testTransformToMap() {
        ImmutableMap<String, String> transToMap =
                FluentIterable.from(personList).transform(new Function<Person, String>() {
                    @Override
                    public String apply(Person input) {
                        return Joiner.on('#').join(input.lastName, input.firstName, input.age);
                    }
                }).toMap(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return "The mapped Value is another Generic Type";
                    }
                });
//        以上可替换为如下JavaAPI的lambda表达方法
//        Map<String, String> transToMap = personList.stream()
//                .map(input -> Joiner.on('#').join(input.lastName, input.firstName, input.age))
//                .collect(Collectors.toMap(java.util.function.Function.identity(), input -> "The mapped Value is another Generic Type"));
        Assert.assertThat(transToMap.containsKey("Flintstone#Wilma#30"), Is.is(true));
        System.out.println(transToMap);
    }
}
