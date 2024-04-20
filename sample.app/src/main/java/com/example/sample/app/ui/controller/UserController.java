package com.example.sample.app.ui.controller;

import com.example.sample.app.ui.model.request.UserDetailsRequestModel;
import com.example.sample.app.ui.model.response.UserRest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public  String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "limit", defaultValue = "50") int limit,
                            @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        return "get user page : "+page+" limit : "+limit+" sort : "+sort;
    }


    @GetMapping(path = "/{userId}",
            produces =  {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        UserRest res=new UserRest();
        res.setUserID(userId);
        res.setFirstName("Bhavika");
        res.setLastName("Arage");
//        return res;
        return new ResponseEntity<UserRest>(res, HttpStatus.OK);
    }

    @PostMapping(consumes =  {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces =  {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public  ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        UserRest res=new UserRest();
        res.setFirstName("Bhavika");
        res.setLastName("Arage");
        return new ResponseEntity<UserRest>(res, HttpStatus.OK);
    }

    @PutMapping
    public  String updateUser() {
        return "update user";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user";
    }
}
