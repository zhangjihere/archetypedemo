package org.tombear.demo.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class Book implements Comparable<Book> {
    private Person author;
    private String title;
    private String publisher;
    private double price;
    private String isbn;

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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

    public static List<Book> createBookList() {
        Person person1 = new Person();
        person1.setAge(12);
        person1.setName("Tom");
        Book book1 = new Book();
        book1.setAuthor(person1);
        book1.setTitle("Apple Cook");
        book1.setPublisher(MoreObjects.firstNonNull(null, "TTS"));
        book1.setIsbn("9527-1");

        Person person2 = new Person();
        person2.setAge(12);
        person2.setName("Tom");
        Book book2 = new Book();
        book2.setAuthor(person2);
        book2.setTitle("Apple Cook");
        book2.setPublisher(MoreObjects.firstNonNull(null, "TTS"));
        book2.setIsbn("9527-2");

        return Lists.newArrayList(book1, book2);
    }
}
