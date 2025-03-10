package com.shami.travel_advisor.hotel;

import java.util.List;

public interface HotelService {
    void createHotels(List<Hotel> hotel);
    List<Hotel> findAllHotels();
    Hotel getHotelById(Integer id);
    boolean updateHotelById(Integer id, Hotel hotel);

    boolean deleteHotelById(Integer id);
}
