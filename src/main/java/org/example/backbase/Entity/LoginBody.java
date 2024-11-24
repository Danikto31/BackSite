package org.example.backbase.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import org.example.backbase.Services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LoginBody {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

//    @JsonProperty("email")
//    private String email;
//
//    @JsonProperty("fullName")
//    private String fullName;
//
//    @JsonProperty("phoneNumber")
//    @Column(nullable = false)
//    private String phoneNumber;

    public LoginBody(){}

//    public LoginBody(String username, String password, String email, String fullName, String phoneNumber){
//        this.password = password;
//        this.username = username;
//        this.email = email;
//        this.fullName = fullName;
//        this.phoneNumber = phoneNumber;
//    }

    public LoginBody(String username, String password){
        this.username = username;
        this.password = password;
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

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
