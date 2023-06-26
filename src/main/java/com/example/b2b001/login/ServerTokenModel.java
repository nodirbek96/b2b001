package com.example.b2b001.login;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "_server_token")
public class ServerTokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String etm_auth_key;
    private String locale;
    private String currency;
    private String max_expiry_time;
    private String max_timeout;
}
