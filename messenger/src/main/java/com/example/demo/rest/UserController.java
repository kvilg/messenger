package com.example.demo.rest;

import com.example.demo.model.*;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.servis.MessengerService;
import com.example.demo.servis.UserService;

import com.solidfire.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.model.Constants.TOKEN_PREFIX;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MessengerService messengerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "/friend/send")
    public @ResponseBody String friendSend(@RequestBody String json,@RequestHeader("Authorization") String token) throws Exception {

        System.out.println(json);

        Gson gson = new Gson();
        FriendSend gsonUser = gson.fromJson(json, FriendSend.class);

        System.out.println(token);
        System.out.println(token.substring(TOKEN_PREFIX.length()));

        String userLogin = jwtTokenUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));



        service.addFriendSend(userLogin,gsonUser.friend);



        return "ok";
    }

    @PostMapping(path = "/friend/add")
    public @ResponseBody String friendAdd(@RequestBody String json,@RequestHeader("Authorization") String token) throws Exception {

        System.out.println(json);

        Gson gson = new Gson();
        FriendSend gsonUser = gson.fromJson(json, FriendSend.class);

        String userLogin = jwtTokenUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));



        service.addFriend(userLogin,gsonUser.friend);
        messengerService.createMessenger(userLogin,gsonUser.friend);



        return "ok";
    }

//    @PostMapping(path = "/find")
//    public @ResponseBody String findUser(@RequestBody String json,@RequestHeader("Authorization") String token) throws Exception {
//
//        Gson gson = new Gson();
//        FriendSend gsonUser = gson.fromJson(json, FriendSend.class);
//
//        String userLogin = jwtTokenUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
//
//        service.addFriend(userLogin,gsonUser.friend);
//        messengerService.createMessenger(userLogin,gsonUser.friend);
//
//
//        ListUser g = new ListUser(service.findUser("us"));
//        String jsonOut = gson.toJson(g);
//
//        return jsonOut;
//    }




}
