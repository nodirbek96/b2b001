package com.example.b2b001.user;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String fio;
    private String email;
    private String phone;
    private String login;
    private String user_types_id;
    private String user_roles_id;
    private String token;
    private String language;
    private String status;
}
