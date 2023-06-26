package com.example.b2b001.hotel.search;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Hotel {
    private String source_id;
    private String cityname;
    private String latitude;
    private String longitude;
    private String name;
    private String address;
}
