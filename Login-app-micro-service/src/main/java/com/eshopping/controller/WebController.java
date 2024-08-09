package com.eshopping.controller;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model model) {
        Login loggedInUser = loginService.signIn(login);
        if (loggedInUser != null) {
            model.addAttribute("name", loggedInUser.getName());
            return "home";
        } else {
            model.addAttribute("message", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Login login, Model model) {
        login.setTypeofuser("customer"); // Set default type to "customer"
        String message = loginService.signUp(login);
        model.addAttribute("message", message);
        return "register";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        // Add any logic you need to prepare data for the home page.
        return "home";
    }
}

