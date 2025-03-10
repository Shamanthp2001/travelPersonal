package com.shami.travel_advisor.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,String> {
    List<City> findByName(String name);
}
