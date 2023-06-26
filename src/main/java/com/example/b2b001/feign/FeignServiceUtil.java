package com.example.b2b001.feign;


import com.example.b2b001.hotel.search.HotelSearchRequest;
import com.example.b2b001.hotel.search.HotelSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@Component
@FeignClient(name = "feignDemo", url = "${etm.url.base}")
public interface FeignServiceUtil {
    @GetMapping("${etm.url.search}")
    HotelSearchResponse getDestinations(@RequestHeader(name = "etm-auth-key") String etmAuthKey,
                                        @SpringQueryMap HotelSearchRequest hotelSearchRequest);
}
