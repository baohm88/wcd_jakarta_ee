package com.t2404e.wellcometojavaweb.entity;

public class RegisterInfo {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterInfo() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.confirmPassword = "";
    }

    public RegisterInfo(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
