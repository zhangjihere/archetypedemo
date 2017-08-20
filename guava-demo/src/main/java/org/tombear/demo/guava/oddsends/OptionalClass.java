package org.tombear.demo.guava.oddsends;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.tombear.demo.guava.Book;

import java.util.Optional;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 23:20.
 */
public class OptionalClass {

    public static void main(String[] args) {
        Book book = new Book();
        Optional<Book> tradeAccountOptional = Optional.of(book);
        Assert.assertThat(tradeAccountOptional.isPresent(), Is.is(true));

        Optional<Book> tradeAccountOptional_empty = Optional.ofNullable(null);
        Assert.assertThat(tradeAccountOptional.isPresent(), Is.is(false));
        tradeAccountOptional.get();
    }
}
