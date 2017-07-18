package org.tombear.demo.guava.functional;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import org.junit.Test;
import org.tombear.demo.guava.City;
import org.tombear.demo.guava.State;

import java.util.Map;

/**
 * <P>Supplier以及Suppliers类</P>
 *
 * @author tombear on 2017-07-16 19:33.
 */
public class SuppliersClass {
    final static Map<String, State> stateMap = State.createStateMapData();
    final static Map<String, City> citiesMap = City.createCityMapData();

    @Test
    public void momerizeMethod() {
        //一个Supplier
        Supplier<Predicate<String>> supplier = new Supplier<Predicate<String>>() {
            @Override
            public Predicate<String> get() {
                return new Predicate<String>() {
                    @Override
                    public boolean apply(String input) {
                        return Strings.isNullOrEmpty(input);
                    }
                };
            }
        };
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        System.out.println("1.注意以上两个为不同的两个对象，如果转成lambda表达式后则为同一个");
        System.out.println("2.如果是Lambda表达式的话，则下面的memorize则没有必要了，猜测Java的Lambda表达式是预先编译或者JVM预先检测创建唯一的对象及内存空间？？");
        //一个缓存Supplier，每一次的get调用，都已同一个对象
        Supplier<Predicate<String>> memoSupplier = Suppliers.memoize(supplier);
        System.out.println(memoSupplier.get());
        System.out.println(memoSupplier.get());
    }
}

