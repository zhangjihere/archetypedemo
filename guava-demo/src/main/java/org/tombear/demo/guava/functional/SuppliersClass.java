package org.tombear.demo.guava.functional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <P>Supplier以及Suppliers类</P>
 *
 * @author tombear on 2017-07-16 19:33.
 */
public class SuppliersClass {
    final static Map<String, FunctionsClass.State> stateMap = new HashMap<>();
    final static Set<FunctionsClass.City> cities = new HashSet<>();
    final static FunctionsClass.State state = new FunctionsClass.State(null, null, null);
    final static Map<String, FunctionsClass.City> cityisMap = new HashMap<>();

    //初始化
    static {
        FunctionsClass.City albany = new FunctionsClass.City("Albany", "1001", 20013);
        FunctionsClass.City buffalo = new FunctionsClass.City("Buffalo", "1002", 99331);
        FunctionsClass.City newYorkCity = new FunctionsClass.City("NewYorkCity", "1003", 41721);

        cityisMap.put("albany", albany);
        cityisMap.put("buffalo", buffalo);
        cityisMap.put("newYorkCity", newYorkCity);

        cities.add(albany);
        cities.add(buffalo);
        cities.add(newYorkCity);
        state.name = "New York";
        state.code = "1000";
        state.mainCities = cities;

        stateMap.put("NY", state);
    }

    // 州
    static class State {
        State(String name, String code, Set<FunctionsClass.City> mainCities) {
            this.name = name;
            this.code = code;
            this.mainCities = mainCities;
        }

        String name;
        String code;
        Set<FunctionsClass.City> mainCities = new HashSet<>();

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("name", name).add("code", code).add("mainCities", mainCities).toString();
        }
    }

    // 市
    static class City {
        City(String name, String zipCode, int population) {
            this.name = name;
            this.zipCode = zipCode;
            this.population = population;
        }

        String name;
        String zipCode;
        int population;

        @Override
        public String toString() {
            return this.name;
        }
    }
    //////////////////////////////////////////////

    @Test
    public void momerizeMethod() {
        //一个Supplier
        Supplier<Predicate<String>> supplier = new Supplier<Predicate<String>>() {
            @Override
            public Predicate<String> get() {
                return  new Predicate<String>() {
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

