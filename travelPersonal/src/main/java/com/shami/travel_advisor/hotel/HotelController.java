package com.shami.travel_advisor.hotel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/travel/hotel")
public class HotelController extends Throwable{
    private final HotelService hotelService;
    public HotelController(HotelService hotelService) {
       this.hotelService = hotelService;
    }
    @PostMapping
    public ResponseEntity<String> createHotels(@RequestBody List<Hotel> hotels){
        hotelService.createHotels(hotels);
        return new ResponseEntity<>(hotels.size()+" Hotels added Successfully",HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> findAllHotels(){
        if (hotelService.findAllHotels().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hotelService.findAllHotels(),HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
//    @RequestMapping(value ="/travel/hotel/{id}",method = RequestMethod.GET)
    public ResponseEntity<Hotel> getHotelById (@PathVariable Integer id) {
        Hotel hotel= hotelService.getHotelById(id);
        try {
            if (Objects.nonNull(hotel.getId())){
                return new ResponseEntity<>(hotel, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping
    public List<Hotel> deleteHotelByAmount(){
        hotelService.findAllHotels().removeIf(hotel -> hotel.getPrice() > 2000);
        return hotelService.findAllHotels();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable Integer id){
        try {
            if (hotelService.deleteHotelById(id)){
                return new ResponseEntity<>("Hotel Deleted Successfully of  "+id,HttpStatus.OK);
            }
            return new ResponseEntity<>("No hotel found ="+id,HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>("Error..",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotelById(@PathVariable Integer id,@RequestBody Hotel hotel){
        try {
            if (id.equals(hotel.getId()) && hotelService.updateHotelById(id,hotel)){
                return new ResponseEntity<>("Hotel updated Succellfully for id="+id,HttpStatus.FOUND);
            }
            return new ResponseEntity<>("Not able to update for id= "+id,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error ..",HttpStatus.NOT_FOUND);
        }
    }
}
