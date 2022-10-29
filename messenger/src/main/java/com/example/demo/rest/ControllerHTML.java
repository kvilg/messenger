package com.example.demo.rest;

import com.example.demo.model.User;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.example.demo.model.Constants.TOKEN_PREFIX;



@Controller
public class ControllerHTML {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserService service;


    @GetMapping(path = "/")
    public String d() {


        return "index";
    }

    @GetMapping(path = "/html")
    public String getUserByJWT(@CookieValue(value = "jwt") String token) {


        String userLogin = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(userLogin);



        return "ht";
    }

//    @GetMapping(path = "/getUser")
//    public String getUsers(@CookieValue(value = "jwt") String token,@RequestHeader("name") String name) {
//
//
//        String userLogin = jwtTokenUtil.getUsernameFromToken(token);
//
//        List<User> userList = service.findUser(name);
//
//        StringBuilder sb = new StringBuilder();
//
//
//        for (int i = 0; i < userList.size(); i++) {
//            sb.append(userList.get(i));
//            sb.append("; ");
//        }
//
//
//
//        return sb.toString();
//    }

}
