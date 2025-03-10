package com.shami.travel_advisor.city;

import com.shami.travel_advisor.hotel.Hotel;

import java.util.List;

public interface CityService {
    List<City> getAllCities();
    boolean addCities(List<City> cityList);
    City getCityByName(String name);
    boolean updateCityByName(String name, City city);
    boolean deleteCityByName(String name);

    List<Hotel> findByName(String name);
}
