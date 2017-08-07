package org.tombear.demo.guava;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 市
 * Created by ji.zhang on 7/18/17.
 */
public class City {
    public City(String name, String zipCode, int population) {
        this.name = name;
        this.zipCode = zipCode;
        this.population = population;
    }

    private String name;
    private String zipCode;
    private int population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name).add("population", population)
                .toString();
    }

    public static Map<String, City> createCityMapData() {
        Map<String, City> citiesMap = Maps.newHashMap();

        City albany = new City("Albany", "1001", 20013);
        City buffalo = new City("Buffalo", "1002", 99331);
        City newYorkCity = new City("NewYorkCity", "1003", 41721);

        citiesMap.put("albany", albany);
        citiesMap.put("buffalo", buffalo);
        citiesMap.put("newYorkCity", newYorkCity);

        return citiesMap;
    }
}