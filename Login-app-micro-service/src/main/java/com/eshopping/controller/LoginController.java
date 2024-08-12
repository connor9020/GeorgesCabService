package com.eshopping.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signIn(@RequestBody Login login, HttpSession session) {
        Login loggedInUser = loginService.signIn(login);
        if (loggedInUser != null) {
            session.setAttribute("name", loggedInUser.getName());
            return ResponseEntity.ok(Map.of("message", "Login successful", "name", loggedInUser.getName()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password"));
        }
    }


    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUp(@RequestBody Login login) {
        String signUpResponse = loginService.signUp(login);
        return ResponseEntity.ok(signUpResponse);
    }
}
