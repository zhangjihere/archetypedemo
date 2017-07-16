package org.tombear.demo.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

/**
 * toString
 * hashCode
 * firstNonNull
 * compareTo
 * <p>
 *     使用了 MoreObjects 类和 ComparisonChain 类
 * Created by ji.zhang on 7/14/17.
 */
public class MoreObjectsClass {

    static class Person implements Comparable<Person> {
        String name;
        int age;

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
    }

    static class Book implements Comparable<Book> {
        Person author;
        String title;
        String publisher;
        double price;

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .omitNullValues()
                    .add("title", title).add("author", author).add("publisher", publisher).add("price", price)
                    .toString();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(author, title, publisher, price);
        }

        @Override
        public int compareTo(Book o) {
            return ComparisonChain.start()
                    .compare(author, o.author)
                    .compare(title, o.title)
                    .compare(publisher, o.publisher)
                    .compare(publisher, o.publisher)
                    .compare(price, o.price)
                    .result();
        }
    }

    @Test
    public void moTest() {
        Person person = new Person();
        person.age = 12;
        person.name = "Tom";
        Book book = new Book();
        book.author = person;
        book.title = "Apple Cook";
        book.publisher = MoreObjects.firstNonNull(null, "TTS");
        System.out.println(book.toString());

        Person person2 = new Person();
        person2.age = 12;
        person2.name = "Tom";
        Book book2 = new Book();
        book2.author = person;
        book2.title = "Apple Cook";
        book2.publisher = MoreObjects.firstNonNull(null, "TTS");
        System.out.println(book2.toString());

        System.out.printf("book compare book2, result is %d", book.compareTo(book2));
    }
}
