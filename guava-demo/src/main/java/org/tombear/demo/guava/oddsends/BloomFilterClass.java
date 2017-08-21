package org.tombear.demo.guava.oddsends;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import org.tombear.demo.guava.Book;

import java.util.List;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 23:08.
 */
public class BloomFilterClass {
    public static void main(String[] args) throws Exception {
        List<Book> books = Book.createBookList();

        BloomFilter<Book> bloomFilter = BloomFilter.create(BookFunnel.FUNNEL, 5);
        BloomFilter<Book> bloomFilter2 = BloomFilter.create(new BookFunnel2(), 5);
        for (Book book : books) {
            bloomFilter.put(book);
        }

        Book newBook = new Book();
        newBook.setTitle("Apple Cook");
        newBook.setIsbn("9527-1");
        Book book1 = books.get(0);
        System.out.println("book " + book1.getTitle() + " contained " + bloomFilter.mightContain(book1));
        System.out.println("book " + newBook.getTitle() + " contained " + bloomFilter.mightContain(newBook));
    }

    /**
     * 枚举类实现Funnel实例的单例，可序列化等特性
     */
    enum BookFunnel implements Funnel<Book> {
        //This is the single enum value
        FUNNEL;

        public void funnel(Book from, PrimitiveSink into) {
            into.putBytes(from.getIsbn().getBytes(Charsets.UTF_8))
                    .putDouble(from.getPrice());
        }

    }

    /**
     * 声明静态类，非单例，也未标示序列化接口
     */
    static class BookFunnel2 implements Funnel<Book> {
        @Override
        public void funnel(Book book, PrimitiveSink primitiveSink) {
            primitiveSink.putBytes(book.getIsbn().getBytes(Charsets.UTF_8))
                    .putDouble(book.getPrice());
        }
    }
}
