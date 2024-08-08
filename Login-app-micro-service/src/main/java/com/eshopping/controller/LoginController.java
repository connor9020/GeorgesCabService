package com.eshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView signIn(@RequestBody Login login, HttpSession session) {
        String message = loginService.signIn(login);
        ModelAndView mav = new ModelAndView();
        if ("Customer login successfully".equals(message) || "Admin login successfully".equals(message)) {
            session.setAttribute("name", login.getName());
            mav.setViewName("home");
        } else {
            mav.setViewName("login");  // Redirect to login page if authentication fails
            mav.addObject("message", message);
        }
        return mav;
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Login login) {
        return loginService.signUp(login);
    }
}
