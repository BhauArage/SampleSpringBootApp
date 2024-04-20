package com.example.sample.app.userservice.impl;

import com.example.sample.app.shared.Utils;
import com.example.sample.app.ui.model.request.UserDetailsRequestModel;
import com.example.sample.app.ui.model.response.UserRest;
import com.example.sample.app.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImplementation() {

    }
    @Autowired
    public UserServiceImplementation(Utils utils) {
        this.utils=utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest res=new UserRest();
        res.setFirstName(userDetails.getFirstName());
        res.setLastName(userDetails.getLastName());
        res.setEmail(userDetails.getEmail());

        String userID= utils.generateUserID();
        res.setUserID(userID);
        if(users==null) users=new HashMap<>();
        users.put(userID,res);

        return res;
    }
}
