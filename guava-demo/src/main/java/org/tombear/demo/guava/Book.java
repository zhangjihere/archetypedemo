package org.tombear.demo.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class Book implements Comparable<Book> {
    private Person author;
    private String title;
    private String publisher;
    private double price;

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
