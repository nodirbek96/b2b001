package com.example.b2b001.login;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LoginRequest {
    private String login;
    private String password;
}
