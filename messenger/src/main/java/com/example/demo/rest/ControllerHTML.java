package com.example.demo.rest;

import com.example.demo.model.User;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.servis.JwtService;
import com.example.demo.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.List;

import static com.example.demo.model.Constants.TOKEN_PREFIX;



@RestController
public class ControllerHTML {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserService service;

    @Autowired
    private JwtService serviceJwt;

    @GetMapping (path = "/")
    public String d(@RequestHeader("name") String name) throws ParseException {

        serviceJwt.rewoveOldDate();

        return "ok";
    }




//    @GetMapping(path = "/html")
//    public String getUserByJWT(@CookieValue(value = "jwt") String token) {
//
//
//        String userLogin = jwtTokenUtil.getUsernameFromToken(token);
//        System.out.println(userLogin);
//
//
//
//        return "ht";
//    }

}
