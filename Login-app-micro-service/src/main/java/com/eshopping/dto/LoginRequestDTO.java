package com.eshopping.dto;

public class LoginRequestDTO {
    private String emailid;
    private String password;

    // Getters and Setters
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
