package com.eshopping.service;

import com.eshopping.entity.Login;

public interface LoginService {
    String signUp(Login login);
    Login signIn(Login login);
}
