package org.example.backbase.Controllers;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class CookieFactory {

    private final String username;

    private final String password;

    private final String userid;

    public CookieFactory(String userid, String username, String password){
        this.password = password;
        this.username = username;
        this.userid = userid;
    }

    public String genCookieValue(){
        String builder = userid+":"+username +":"+ password;
        return Base64.getUrlEncoder().encodeToString(builder.getBytes(StandardCharsets.UTF_8));
    }

    public static String[] decodeCookieValue(String Base64Encode)
    {
        return new String(Base64.getUrlDecoder().decode(Base64Encode),StandardCharsets.UTF_8).split(":");
    }



}
