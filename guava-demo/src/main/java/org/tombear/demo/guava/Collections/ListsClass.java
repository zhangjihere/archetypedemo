package org.tombear.demo.guava.Collections;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.tombear.demo.guava.Person;

import java.util.List;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class ListsClass {

    @Test
    public void newPartition() {
        List<Person> personList = Lists.newArrayList(Person.createPersonList());
        List<List<Person>> subList = Lists.partition(personList,2);
        System.out.println(subList);
    }
}
