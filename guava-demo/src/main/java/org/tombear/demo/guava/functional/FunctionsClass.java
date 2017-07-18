package org.tombear.demo.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;

import org.junit.Test;
import org.tombear.demo.guava.City;
import org.tombear.demo.guava.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <P>Funcion以及Functions类的使用</P>
 *
 * @author tombear on 2017-07-16 17:12.
 */
public class FunctionsClass {
    final static Map<String, State> stateMap = new HashMap<>();
    final static Set<City> cities = new HashSet<>();
    final static State state = new State(null, null, null);

    //初始化
    static {
        cities.add(new City("Albany", "1001", 20013));
        cities.add(new City("Buffalo", "1002", 99331));
        cities.add(new City("NewYorkCity", "1003", 41721));
        state.setName("New York");
        state.setCode("1000");
        state.setMainCities(cities);
        stateMap.put("NY", state);
    }

    //Functions的forMap方法
    @Test
    public void forMapMethod() {
        Function<String, State> lookup = Functions.forMap(stateMap);
        State state = lookup.apply("NY");
        System.out.println(state);
    }

    //Function的compose方法
    @Test
    public void composeMethod() {
        Function<String, State> lookup = Functions.forMap(stateMap);
        Function<State, String> stateFunction = new Function<State, String>() {
            @Override
            public String apply(State input) {
                return Joiner.on(",").join(input.getMainCities());
            }
        };
        Function<String, String> composed = Functions.compose(stateFunction, lookup);
        System.out.println(composed.apply("NY"));

    }
}
