package com.eshopping.service;

import com.eshopping.entity.Login;
import com.eshopping.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class LoginServiceImplTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_Success() {
        // Arrange
        Login login = new Login();
        login.setEmailid("newuser@example.com");
        login.setPassword("password");
        login.setTypeofuser("customer");

        when(loginRepository.findByEmailid("newuser@example.com")).thenReturn(Optional.empty());

        // Act
        String result = loginService.signUp(login);

        // Assert
        assertEquals("Account created successfully", result);
    }

    @Test
    void testSignUp_EmailAlreadyExists() {
        // Arrange
        Login existingLogin = new Login();
        existingLogin.setEmailid("existing@example.com");
        existingLogin.setPassword("password");
        existingLogin.setTypeofuser("customer");

        when(loginRepository.findByEmailid("existing@example.com")).thenReturn(Optional.of(existingLogin));

        // Act
        Login newLogin = new Login();
        newLogin.setEmailid("existing@example.com");
        newLogin.setPassword("newpassword");
        newLogin.setTypeofuser("customer");
        String result = loginService.signUp(newLogin);

        // Assert
        assertEquals("Emailid must be unique", result);
    }

    @Test
    void testSignUp_AdminAccountNotAllowed() {
        // Act
        Login login = new Login();
        login.setEmailid("admin@example.com");
        login.setPassword("adminpassword");
        login.setTypeofuser("admin");

        String result = loginService.signUp(login);

        // Assert
        assertEquals("You can't create admin account", result);
    }

    @Test
    void testSignIn_Success() {
        // Arrange
        Login login = new Login();
        login.setEmailid("test@example.com");
        login.setPassword("password");

        when(loginRepository.findByEmailid("test@example.com")).thenReturn(Optional.of(login));

        // Act
        Login result = loginService.signIn(login);

        // Assert
        assertEquals(login, result);
    }

    @Test
    void testSignIn_WrongPassword() {
        // Arrange
        Login login = new Login();
        login.setEmailid("test@example.com");
        login.setPassword("password");

        Login wrongPasswordLogin = new Login();
        wrongPasswordLogin.setEmailid("test@example.com");
        wrongPasswordLogin.setPassword("wrongpassword");

        when(loginRepository.findByEmailid("test@example.com")).thenReturn(Optional.of(wrongPasswordLogin));

        // Act
        Login result = loginService.signIn(login);

        // Assert
        assertNull(result);
    }

    @Test
    void testSignIn_EmailNotFound() {
        // Arrange
        Login login = new Login();
        login.setEmailid("nonexistent@example.com");
        login.setPassword("password");

        when(loginRepository.findByEmailid("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act
        Login result = loginService.signIn(login);

        // Assert
        assertNull(result);
    }
}
