package com.example.b2b001.hotel;

import com.example.b2b001.feign.FeignServiceUtil;
import com.example.b2b001.hotel.search.CountryDataFull;
import com.example.b2b001.hotel.search.Hotel;
import com.example.b2b001.hotel.search.HotelSearchRequest;
import com.example.b2b001.hotel.search.HotelSearchResponse;
import com.example.b2b001.login.LoginService;
import com.example.b2b001.login.ServerTokenModel;
import com.example.b2b001.model.ResponseData;
import com.example.b2b001.model.ResponseError;
import com.example.b2b001.model.ResponseModel;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private FeignServiceUtil feignServiceUtil;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${etm.url.base}")
    private String baseUrl;
    @Value("${etm.url.search}")
    private String searchUrl;
    @Value("${etm.token_header}")
    private String tokenHeader;
    public ResponseModel getOpenFeignResult(HotelSearchRequest hotelSearchRequest, String token){
        System.out.println("Token: "+token);
        HotelSearchResponse hotelSearchResponse=new HotelSearchResponse();
        ResponseData responseData=new ResponseData();
        try{
            hotelSearchResponse= feignServiceUtil.getDestinations(token,hotelSearchRequest);
            List<CountryDataFull> countryDataFulls=hotelSearchResponse.getCountryDataFull();
            List<Hotel> hotels=hotelSearchResponse.getHotelDataFull().getHotels();
            responseData.setHotels(hotels);
            responseData.setCountries(countryDataFulls);
            return new ResponseModel(true,
                    responseData,
                    null);
        }catch (FeignException ex){
            System.out.println("Exception: "+ex.status());
            int code=ex.status();
            if(code==403){
                System.out.println("403 forbidden");
                return getDataWhenTokenExpired(hotelSearchRequest,getNewTokenFromServer());

            }else{
                System.out.println("Exception: "+ex.getMessage());
                return new ResponseModel(false,
                        null,
                        new ResponseError(ex.status(),ex.getMessage()));
            }
        }

    }
    private ResponseModel getDataWhenTokenExpired(HotelSearchRequest hotelSearchRequest,String token){
        System.out.println("Token: "+token);
        HotelSearchResponse hotelSearchResponse=
                feignServiceUtil.getDestinations(token,hotelSearchRequest);
        ResponseData responseData=new ResponseData();
        responseData.setCountries(hotelSearchResponse.getCountryDataFull());
        responseData.setHotels(hotelSearchResponse.getHotelDataFull().getHotels());
        return new ResponseModel(true,responseData,null);
    }

    private String getNewTokenFromServer(){
        System.out.println("Called log in from server ");
        ResponseModel responseModel=loginService.makeLoginRequest();
        if(responseModel.isSuccess()){
            ResponseData responseData=responseModel.getData();
            ServerTokenModel serverTokenModel=responseData.getServerTokenModel();
            return serverTokenModel.getEtm_auth_key();
        }else{
            return "LOGIN_ERROR";
        }
    }

   /**
    public String searchHotel(HotelSearchRequest hotelSearchRequest){
        String url=baseUrl+searchUrl;
        System.out.println(url);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add(tokenHeader,token);
        httpHeaders.add("Content-Type","application/json");
        HttpEntity<HotelSearchRequest> httpEntity=new HttpEntity<>(hotelSearchRequest,httpHeaders);
        System.out.println(httpEntity.getBody()+"  "+httpEntity.getHeaders());
        ResponseEntity<HotelSearchResponse> hotelSearchResponse;
        try{
            hotelSearchResponse=restTemplate.exchange(url, HttpMethod.GET,httpEntity,HotelSearchResponse.class);
            return Objects.requireNonNull(hotelSearchResponse.getBody()).toString();
        }catch (HttpStatusCodeException e){
            return e.getMessage();
        }
    }**/
}
