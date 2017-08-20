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
        for (Book book : books) {
            bloomFilter.put(book);
        }

        Book newBook = new Book();
        newBook.setTitle("Mountain Climbing");
        Book book1 = books.get(0);
        System.out.println("book " + book1.getTitle() + " contained " + bloomFilter.mightContain(book1));
        System.out.println("book " + newBook.getTitle() + " contained " + bloomFilter.mightContain(newBook));
    }

    enum BookFunnel implements Funnel<Book> {
        //This is the single enum value
        FUNNEL;

        public void funnel(Book from, PrimitiveSink into) {
            into.putBytes(from.getIsbn().getBytes(Charsets.UTF_8))
                    .putDouble(from.getPrice());
        }

    }
}
