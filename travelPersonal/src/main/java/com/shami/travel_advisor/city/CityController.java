package com.shami.travel_advisor.city;

import com.shami.travel_advisor.hotel.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/travel/city")
public class CityController {
    public CityController(CityService cityService){
        this.cityService=cityService;
    }
    private final CityService cityService;
    @GetMapping
    public ResponseEntity<List<City>> getCities(){
        if (cityService.getAllCities().isEmpty()){
            return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCities(@RequestBody List<City> cityList){
        if (cityService.addCities(cityList)) {
            return new ResponseEntity<>(cityList.size()+" Cities added sucessfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cities not added", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/{name}")
    public ResponseEntity<City> getCityByName (@PathVariable String name) {
        City city= cityService.getCityByName(name);
        try {
            if (Objects.nonNull(city.getName())){
                return new ResponseEntity<>(city, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCityByName(@PathVariable String name){
        try {
            if (cityService.deleteCityByName(name)){
                return new ResponseEntity<>(name+" -City Deleted Successfully  ",HttpStatus.OK);
            }
            return new ResponseEntity<>("No city found of name ="+name,HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>("Error..",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateCityByName(@PathVariable String name,@RequestBody City city){
        try {
            if (name.equals(city.getName()) && cityService.updateCityByName(name,city)){
                return new ResponseEntity<>(name+"- city  updated Successfully",HttpStatus.FOUND);
            }
            return new ResponseEntity<>("Not able to update for city "+name,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error ..",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}/hotel")
    public ResponseEntity<List<Hotel>> getHotelsByCityName(@PathVariable String name){
        return new ResponseEntity<>(cityService.findByName(name),HttpStatus.OK);
    }
}
