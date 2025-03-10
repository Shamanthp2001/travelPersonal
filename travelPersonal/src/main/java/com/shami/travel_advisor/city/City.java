package com.shami.travel_advisor.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shami.travel_advisor.hotel.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city", schema = "public")
public class City {
    @Id
//    @GeneratedValue
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city")   //One city can have multiple hotels
    private List<Hotel> hotelList;

    private LocalDateTime inTime;
    private LocalDateTime outTime;
}
