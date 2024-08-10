package com.eshopping.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshopping.entity.Login;
import com.eshopping.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    public void testSignInSuccess() throws Exception {
        Login login = new Login();
        login.setEmailid("test@example.com");
        login.setPassword("password");
        login.setName("Test User");

        when(loginService.signIn(any(Login.class))).thenReturn(login);

        mockMvc.perform(post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailid\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("name", "Test User"));
    }

    @Test
    public void testSignInFailure() throws Exception {
        when(loginService.signIn(any(Login.class))).thenReturn(null);

        mockMvc.perform(post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailid\":\"test@example.com\",\"password\":\"wrongpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("message", "Invalid email or password"));
    }

    @Test
    public void testSignUp() throws Exception {
        when(loginService.signUp(any(Login.class))).thenReturn("Account created successfully");

        mockMvc.perform(post("/login/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"emailid\":\"test@example.com\",\"password\":\"password\",\"typeofuser\":\"customer\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account created successfully"));
    }
}
