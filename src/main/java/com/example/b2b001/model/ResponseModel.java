package com.example.b2b001.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    private boolean success;
    private ResponseData data;
    private ResponseError error;
}
