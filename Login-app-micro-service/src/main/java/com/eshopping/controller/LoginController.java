package com.eshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signIn(@RequestBody Login login) {
        String message = loginService.signIn(login);
        if ("Customer login successfully".equals(message) || "Admin login successfully".equals(message)) {
            return "redirect:/home";
        } else {
            return message;
        }
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Login login) {
        return loginService.signUp(login);
    }
}
