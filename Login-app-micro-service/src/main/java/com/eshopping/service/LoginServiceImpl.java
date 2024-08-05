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
    public String signIn(Login login) {
        Optional<Login> result = loginRepository.findByEmailid(login.getEmailid());
        if (result.isPresent()) {
            Login ll = result.get();
            if (ll.getPassword().equals(login.getPassword())) {
                if ("admin".equals(ll.getTypeofuser()) && "admin".equals(login.getTypeofuser())) {
                    return "Admin login successfully";
                } else if ("customer".equals(ll.getTypeofuser()) && "customer".equals(login.getTypeofuser())) {
                    return "Customer login successfully";
                } else {
                    return "Type of user is invalid";
                }
            } else {
                return "Password is wrong";
            }
        } else {
            return "Emailid is wrong";
        }
    }
}

