package org.tombear.demo.guava;

/**
 * 州
 * Created by ji.zhang on 7/18/17.
 */

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
    public State(String name, String code, Set<City> mainCities) {
        this.name = name;
        this.code = code;
        this.mainCities = mainCities;
    }

    private String name;
    private String code;
    private Set<City> mainCities = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<City> getMainCities() {
        return mainCities;
    }

    public void setMainCities(Set<City> mainCities) {
        this.mainCities = mainCities;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("code", code).add("mainCities", mainCities).toString();
    }

    public static Map<String, State> createStateMapData() {
        Map<String, State> stateMap = new HashMap<>();
        Set<City> cities = new HashSet<>();
        State state = new State(null, null, null);
        Map<String, City> cityisMap = Maps.newHashMap();

        City albany = new City("Albany", "1001", 20013);
        City buffalo = new City("Buffalo", "1002", 99331);
        City newYorkCity = new City("NewYorkCity", "1003", 41721);

        cityisMap.put("albany", albany);
        cityisMap.put("buffalo", buffalo);
        cityisMap.put("newYorkCity", newYorkCity);

        cities.add(albany);
        cities.add(buffalo);
        cities.add(newYorkCity);

        state.setName("New York");
        state.setCode("1000");
        state.setMainCities(cities);

        stateMap.put("NY", state);
        return stateMap;
    }
}
