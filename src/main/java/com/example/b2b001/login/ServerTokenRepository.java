package com.example.b2b001.login;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ServerTokenRepository extends JpaRepository<ServerTokenModel, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update _server_token set etm_auth_key=:etm_auth_key,locale=:locale,currency=:currency," +
            "max_expiry_time=:max_expiry_time,max_timeout=:max_timeout where id=:id",nativeQuery = true)
    int updateServerToken(String etm_auth_key,
                          String locale,
                          String currency,
                          String max_expiry_time,
                          String max_timeout,
                          int id);
}
