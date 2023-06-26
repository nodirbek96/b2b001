package com.example.b2b001.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public boolean getUserByToken(String token){
        User user=userRepository.getUserByToken(token);
        System.out.println("User is exist = "+isUserExist(user));
        return isUserExist(user);
    }
    private boolean isUserExist(User user){
        return user != null;
    }
}
