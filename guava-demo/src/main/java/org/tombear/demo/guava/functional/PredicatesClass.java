package org.tombear.demo.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <P>Predicate及Predicates工具类</P>
 *
 * @author tombear on 2017-07-16 18:46.
 */
public class PredicatesClass {
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
    public void filterMethod() {
        Predicate<FunctionsClass.City> populationPredicate = new Predicate<FunctionsClass.City>() {
            @Override
            public boolean apply(FunctionsClass.City input) {
                return input.population <= 50000;
            }
        };
        Predicate<FunctionsClass.City> zipCodePredicate = new Predicate<FunctionsClass.City>() {
            @Override
            public boolean apply(FunctionsClass.City input) {
                return input.zipCode.compareTo("1002") >= 0;
            }
        };
        Predicate<FunctionsClass.City> andPredicate = Predicates.and(populationPredicate, zipCodePredicate);
        System.out.println(andPredicate.apply(new FunctionsClass.City("NewYorkCity", "1003", 41721)));

        //compose
        Function<String, FunctionsClass.City> citiesLookup = Functions.forMap(cityisMap);
        Predicate<String> compose = Predicates.compose(andPredicate, citiesLookup);
        System.out.println(compose.apply("newYorkCity"));

    }
}
