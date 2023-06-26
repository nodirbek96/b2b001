package com.example.b2b001.login;

import com.example.b2b001.model.ResponseData;
import com.example.b2b001.model.ResponseError;
import com.example.b2b001.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServerTokenRepository serverTokenRepository;
    @Value("${etm.url.base}")
    private String baseUrl;
    @Value("${etm.url.login}")
    private String loginUrl;
    @Value("${etm.login}")
    private String login;
    @Value("${etm.password}")
    private String password;

    public ResponseModel makeLoginRequest() {
        LoginRequest loginRequest = new LoginRequest(login, password);
        String url = baseUrl + loginUrl;
        try {
            ResponseData responseData=new ResponseData();
            ServerTokenModel serverTokenModel = restTemplate.postForObject(url, loginRequest, ServerTokenModel.class);
            assert serverTokenModel != null;
            if(serverTokenRepository.existsById(1)){
                int a=updateServerTokenTable(serverTokenModel);
                responseData.setServerTokenModel(serverTokenModel);
                if(a==1){
                    System.out.println("Token Updated...");
                }else{
                    System.out.println("Token not updated");
                }
            }else{
                System.out.println("Token inserted");
                serverTokenRepository.save(serverTokenModel);
            }
            return new ResponseModel(true, responseData, null);
        } catch (HttpStatusCodeException e) {
            return new ResponseModel(false,
                    null,
                    new ResponseError(e.getStatusCode().value(), e.getMessage()));
        }
    }

    private int updateServerTokenTable(ServerTokenModel serverTokenModel) {
        String etm_auth_key = serverTokenModel.getEtm_auth_key();
        String locale = serverTokenModel.getLocale();
        String currency = serverTokenModel.getCurrency();
        String max_expiry_time = serverTokenModel.getMax_expiry_time();
        String max_timeout = serverTokenModel.getMax_timeout();
        return serverTokenRepository.updateServerToken(etm_auth_key,
                locale,
                currency,
                max_expiry_time,
                max_timeout,
                1);
    }
    public String getTokenFromDB(){
        Optional<ServerTokenModel> serverTokenModel=serverTokenRepository.findById(1);
        return serverTokenModel.get().getEtm_auth_key();
    }
}
