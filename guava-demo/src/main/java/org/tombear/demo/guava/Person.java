package org.tombear.demo.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Supplier;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private String name;
    private int age;
    private String sex;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }

    public Person(String firstName, String lastName, String name, int age, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name).add("age", age)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age);
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start()
                .compare(name, o.name)
                .compare(age, o.age)
                .result();
    }

    /**
     * @return 准备集合数据
     */
    public static List<Person> createPersonList() {
        Supplier<List<Person>> supplierPersonList = new Supplier<List<Person>>() {
            @Override
            public List<Person> get() {
                Person person1 = new Person("Wilma", "Flintstone", 25, "F");
                Person person2 = new Person("Fred", "Flintstone", 44, "M");
                Person person3 = new Person("Betty", "Rubble", 31, "F");
                Person person4 = new Person("Barney", "Rubble", 55, "M");
                return Lists.newArrayList(person1, person2, person3, person4);
            }
        };
        return supplierPersonList.get();
    }

}
