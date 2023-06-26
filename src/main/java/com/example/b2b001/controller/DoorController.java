package com.example.b2b001.controller;


import com.example.b2b001.hotel.HotelService;
import com.example.b2b001.hotel.search.HotelSearchRequest;
import com.example.b2b001.login.LoginService;
import com.example.b2b001.model.ResponseError;
import com.example.b2b001.model.ResponseModel;
import com.example.b2b001.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/b2b")
public class DoorController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;
    @PostMapping("/etm/login")
    public ResponseModel makeLoginEtmServer(){
        return loginService.makeLoginRequest();
    }

    @PostMapping("/hotel/search")
    public ResponseModel searchOpenFeign(@RequestHeader("auth-token") String token, @RequestBody HotelSearchRequest hotelSearchRequest){
        if(userService.getUserByToken(token)){
            return hotelService.getOpenFeignResult(hotelSearchRequest,loginService.getTokenFromDB());
        }else{
            return new ResponseModel(false,null,new ResponseError(203,"User not found or token is expired. Get New Token and try again"));
        }
    }
}
