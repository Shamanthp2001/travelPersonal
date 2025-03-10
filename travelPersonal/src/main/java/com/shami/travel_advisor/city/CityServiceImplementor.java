package com.shami.travel_advisor.city;

import com.shami.travel_advisor.hotel.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImplementor implements CityService{
    public CityServiceImplementor(CityRepository cityRepository){
        this.cityRepository=cityRepository;
    }
    CityRepository cityRepository;
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public boolean addCities(List<City> cityList) {
        if (!cityList.isEmpty()){
            cityRepository.saveAll(cityList);
            return true;
        }
        return false;
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.findById(name).orElse(null);
    }

    @Override
    public boolean updateCityByName(String name, City city) {
        Optional<City> cityOptional=cityRepository.findById(name);
        if (cityOptional.isPresent()){
            City city1 = cityOptional.get();
            city1.setHotelList(city.getHotelList());
            city1.setInTime(city.getInTime());
            city1.setOutTime(city.getOutTime());
            cityRepository.save(city1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCityByName(String name) {
        if (cityRepository.findById(name).isPresent()){
            cityRepository.deleteById(name);
            return true;
        }
        return false;
    }

    @Override
    public List<Hotel> findByName(String name) {
        List<Hotel> hotelList=new ArrayList<>();
        for (City city: cityRepository.findByName(name)){
            hotelList.addAll(city.getHotelList());
        }
        return hotelList;
    }
}
