package org.example.backbase.Entity;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class LoginBody {

    private String username;

    private String password;

    private String email;

    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    public LoginBody(){}

    public LoginBody(String username, String password, String email, String fullName, String phoneNumber){
        this.password = password;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
