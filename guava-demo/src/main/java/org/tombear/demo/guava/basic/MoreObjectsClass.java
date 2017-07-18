package org.tombear.demo.guava.basic;

import com.google.common.base.MoreObjects;
import org.junit.Test;
import org.tombear.demo.guava.Book;
import org.tombear.demo.guava.Person;

/**
 * toString
 * hashCode
 * firstNonNull
 * compareTo
 * @See Person
 * <p>
 *     使用了 MoreObjects 类和 ComparisonChain 类
 * Created by ji.zhang on 7/14/17.
 */
public class MoreObjectsClass {

    @Test
    public void moTest() {
        Person person = new Person();
        person.setAge(12);
        person.setName("Tom");
        Book book = new Book();
        book.setAuthor(person);
        book.setTitle("Apple Cook");
        book.setPublisher(MoreObjects.firstNonNull(null, "TTS"));
        System.out.println(book.toString());

        Person person2 = new Person();
        person2.setAge(12);
        person2.setName("Tom");
        Book book2 = new Book();
        book2.setAuthor(person2);
        book2.setTitle("Apple Cook");
        book2.setPublisher(MoreObjects.firstNonNull(null, "TTS"));
        System.out.println(book2.toString());

        System.out.printf("book compare book2, result is %d", book.compareTo(book2));
    }
}
