package com.shami.travel_advisor.hotel;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImplementor implements HotelService {
    public HotelServiceImplementor(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }
    HotelRepository hotelRepository;
    int id=1;

    @Override
    public void createHotels(List<Hotel> hotel) {
        for (Hotel hotel1:hotel){
            hotel1.setId(this.id);
            id++;
            this.hotelRepository.save(hotel1);
        }

    }

    @Override
    public List<Hotel> findAllHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Integer id) {
        return hotelRepository.findById(id).orElse(null);
    }

    /**
     * UPdate hotel by using primary key or ID
     * @param id Integer
     * @param hotel Hotel
     * @return boolean
     */
    @Override
    public boolean updateHotelById(Integer id, Hotel hotel) {
        Optional<Hotel> hotelOptional=hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            Hotel hotel1=hotelOptional.get();
            hotel1.setName(hotel.getName());
            hotel1.setLocation(hotel.getLocation());
            hotel1.setInTime(hotel.getInTime());
            hotel1.setOutTime(hotel.getOutTime());
            hotel1.setPrice(hotel.getPrice());
            hotelRepository.save(hotel1);
            return true;
        }
       return false;
    }

    @Override
    public boolean deleteHotelById(Integer id) {
        if (hotelRepository.findById(id).isPresent()) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
