package com.example.b2b001.hotel.search;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Country {
    private int id;
    private String code;
    private String code_rus;
    private String name;
    private String citizenship_code;
    private int phone;
    private String locale;
    private int max_phone_length;
    private int min_phone_length;
}
