package com.example.sample.app.userservice;

import com.example.sample.app.ui.model.request.UserDetailsRequestModel;
import com.example.sample.app.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
