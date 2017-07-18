package org.tombear.demo.guava.Collections;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.tombear.demo.guava.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ji.zhang on 7/18/17.
 */
public class MapsClass {

    /**
     * 把集合元素作为Value，把元素的某属性作为Key
     */
    @Test
    public void uniqueIndexMethod() {
        List<Book> books = Book.createBookList();
        Map<String, Book> bookMap = Maps.uniqueIndex(books.iterator(), new Function<Book, String>() {
            @Override
            public String apply(Book input) {
                return input.getIsbn();
            }
        });
        System.out.println(bookMap);
    }

    /**
     * 与uniqueIndex方法相反，把集合元素作为Key，把元素的某属性作为Value
     */
    @Test
    public void asMapMethod() {
        List<Book> books = Book.createBookList();
        Set<Book> bookss = Sets.newHashSet(books);

        Map<Book, String> bookMap = Maps.asMap(bookss, new Function<Book, String>() {
            @Override
            public String apply(Book input) {
                return input.getIsbn();
            }
        });
        System.out.println(bookMap);
    }
}
