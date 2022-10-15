package com.example.demo.rest;



import com.example.demo.model.User;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserOut;
import com.example.demo.security.JwtTokenUtil;
import com.solidfire.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.example.demo.servis.UserService;
import static com.example.demo.model.Constants.TOKEN_PREFIX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserService service;




    @PostMapping(path = "/login")
    public String getAuthUser(@RequestBody String json){

        Gson gson = new Gson();
        UserAuth gsonUser = gson.fromJson(json, UserAuth.class);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(gsonUser.login, gsonUser.password));
        com.example.demo.model.User user = service.getByLogin(gsonUser.login);
        String token = jwtTokenUtil.generateToken(user);

        UserOut g = new UserOut(user,TOKEN_PREFIX+token);
        String jsonOut = gson.toJson(g);

        return jsonOut;
    }


    @GetMapping(path = "/login/jwt")
    public String getUserByJWT(@RequestHeader("Authorization") String token) {


        String a = token.substring(TOKEN_PREFIX.length());
        String userLogin = jwtTokenUtil.getUsernameFromToken(a);

        User user = service.getByLogin(userLogin);




        Gson gson = new Gson();
        UserOut g = new UserOut(user);
        String jsonOut = gson.toJson(g);

        return jsonOut;
    }

    @PostMapping(path = "/registration")
    public String registration(@RequestBody String json) throws Exception {

        Gson gson = new Gson();
        UserAuth gsonUser = gson.fromJson(json, UserAuth.class);

        service.registration(gsonUser.name,gsonUser.login,gsonUser.password);



        return "ok";
    }


/** для того что бы токен авторизировал нужен префикс Bearer {token}
 *
 * PS. ЭТО ТОЛЬКО ДЛЯ ЗНАЧЕНИЯ AUTORIZAIFION NE JWT
 * */
}
