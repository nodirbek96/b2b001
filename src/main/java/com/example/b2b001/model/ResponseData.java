package com.example.b2b001.model;

import com.example.b2b001.hotel.search.CountryDataFull;
import com.example.b2b001.hotel.search.Hotel;
import com.example.b2b001.login.ServerTokenModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private ServerTokenModel serverTokenModel;
    private List<CountryDataFull> countries;
    private List<Hotel> hotels;

}
