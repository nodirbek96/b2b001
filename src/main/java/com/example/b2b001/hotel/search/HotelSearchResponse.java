package com.example.b2b001.hotel.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class HotelSearchResponse {
    @JsonProperty("data")
    private List<CountryDataFull> countryDataFull;
    @JsonProperty("meta")
    private HotelDataFull hotelDataFull;
}
