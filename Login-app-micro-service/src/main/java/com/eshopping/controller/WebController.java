package com.eshopping.controller;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model model) {
        String message = loginService.signIn(login);
        if ("Customer login successfully".equals(message) || "Admin login successfully".equals(message)) {
            return "redirect:/home";
        } else {
            model.addAttribute("message", message);
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
    public String showHomePage() {
        return "home";
    }

    // Example methods to call cab-booking and cab-fare microservices
//    private String callCabBookingService() {
//        String url = "http://localhost:8083/cab-booking"; // Replace with your cab-booking service endpoint
//        return restTemplate.getForObject(url, String.class);
//    }
//
//    private String callCabFareService() {
//        String url = "http://localhost:8084/cab-fare"; // Replace with your cab-fare service endpoint
//        return restTemplate.getForObject(url, String.class);
//    }
}
