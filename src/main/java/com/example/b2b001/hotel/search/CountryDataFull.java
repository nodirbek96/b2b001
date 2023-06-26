package com.example.b2b001.hotel.search;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CountryDataFull {
    private int id;
    private int giata_id;
    private Country country;
    private String latitude;
    private String longitude;
    private String iata;
    private String name;
    private String full_name;
}
