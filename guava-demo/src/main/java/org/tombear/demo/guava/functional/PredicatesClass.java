package org.tombear.demo.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.junit.Test;
import org.tombear.demo.guava.City;

import java.util.Map;

/**
 * <P>Predicate及Predicates工具类</P>
 *
 * @author tombear on 2017-07-16 18:46.
 */
public class PredicatesClass {
    private final Map<String, City> citiesMap = City.createCityMapData();

    @Test
    public void filterMethod() {
        Predicate<City> populationPredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getPopulation() <= 50000;
            }
        };
        Predicate<City> zipCodePredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getZipCode().compareTo("1002") >= 0;
            }
        };
        Predicate<City> andPredicate = Predicates.and(populationPredicate, zipCodePredicate);
        System.out.println(andPredicate.apply(new City("NewYorkCity", "1003", 41721)));

        //compose
        Function<String, City> citiesLookup = Functions.forMap(citiesMap);
        Predicate<String> compose = Predicates.compose(andPredicate, citiesLookup);
        System.out.println(compose.apply("newYorkCity"));

    }
}
