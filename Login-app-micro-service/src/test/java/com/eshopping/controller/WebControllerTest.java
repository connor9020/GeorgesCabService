package com.eshopping.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    public void testShowLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("login"));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        Login login = new Login();
        login.setEmailid("test@example.com");
        login.setPassword("password");
        login.setName("Test User");

        when(loginService.signIn(any(Login.class))).thenReturn(login);

        mockMvc.perform(post("/login")
                .param("emailid", "test@example.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("name", "Test User"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        when(loginService.signIn(any(Login.class))).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("emailid", "test@example.com")
                .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("message", "Invalid credentials"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("login"));
    }

    @Test
    public void testRegister() throws Exception {
        when(loginService.signUp(any(Login.class))).thenReturn("Account created successfully");

        mockMvc.perform(post("/register")
                .param("emailid", "test@example.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attribute("message", "Account created successfully"));
    }

    @Test
    public void testShowHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
