package com.example.sample.app.ui.controller;

import com.example.sample.app.ui.model.request.UpdatedUserDetailsRequestModel;
import com.example.sample.app.ui.model.request.UserDetailsRequestModel;
import com.example.sample.app.ui.model.response.UserRest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;

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
        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        res.setFirstName(userDetails.getFirstName());
        res.setLastName(userDetails.getLastName());
        res.setEmail(userDetails.getEmail());

        String userID= UUID.randomUUID().toString();
        res.setUserID(userID);
        if(users==null) users=new HashMap<>();
        users.put(userID,res);

        return new ResponseEntity<UserRest>(res, HttpStatus.OK);
    }

    @PutMapping(path = "/{userID}", consumes =  {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces =  {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public  UserRest updateUser(@PathVariable String userID,@Valid @RequestBody UpdatedUserDetailsRequestModel userDetails) {
        UserRest stored=users.get(userID);
        stored.setFirstName(userDetails.getFirstName());
        stored.setLastName(userDetails.getLastName());
        users.put(userID,stored);
        return  stored;
    }

    @DeleteMapping(path = "/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userID){
        users.remove(userID);
        return ResponseEntity.noContent().build();

    }
}
