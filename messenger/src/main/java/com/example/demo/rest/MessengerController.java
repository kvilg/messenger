package com.example.demo.rest;

import com.example.demo.model.FriendSend;
import com.example.demo.model.MessageSend;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.servis.MessageService;
import com.example.demo.servis.MessengerService;
import com.example.demo.servis.UserService;
import com.solidfire.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.model.Constants.TOKEN_PREFIX;



@RestController
@RequestMapping("/messenger")
public class MessengerController {


    @Autowired
    private UserService service;

    @Autowired
    private MessengerService messengerService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping(path = "/created")
    public @ResponseBody String messengerAdd(@RequestBody String json,@RequestHeader("Authorization") String token) throws Exception {

        Gson gson = new Gson();
        FriendSend gsonUser = gson.fromJson(json, FriendSend.class);

        String userLogin = jwtTokenUtil.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));

        messengerService.createMessenger(userLogin,gsonUser.friend);

        return "ok";
    }



    @PostMapping(path = "/message/add")
    public @ResponseBody String messageAdd(@RequestBody String json, @RequestHeader("Authorization") String token) throws Exception {

        Gson gson = new Gson();
        MessageSend gsonUser = gson.fromJson(json, MessageSend.class);

        messageService.sendMessageInMessenger(gsonUser.idMessenger,gsonUser.User,gsonUser.textMessage);

        return "ok";
    }
}
