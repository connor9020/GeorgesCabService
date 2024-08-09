package com.eshopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.entity.Login;
import com.eshopping.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public String signUp(Login login) {
        if (loginRepository.findByEmailid(login.getEmailid()).isPresent()) {
            return "Emailid must be unique";
        } else if ("admin".equals(login.getTypeofuser())) {
            return "You can't create admin account";
        } else {
            loginRepository.save(login);
            return "Account created successfully";
        }
    }
    @Override
    public Login signIn(Login login) {
        Optional<Login> result = loginRepository.findByEmailid(login.getEmailid());
        if (result.isPresent()) {
            Login ll = result.get();
            if (ll.getPassword().equals(login.getPassword())) {
                return ll;  // Return the Login object with user details
            } else {
                return null;  // Password is incorrect
            }
        } else {
            return null;  // Email ID is incorrect
        }
    }

}

