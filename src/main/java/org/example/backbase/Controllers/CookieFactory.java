package org.example.backbase.Controllers;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class CookieFactory {

    private final String username;

    private final String password;

    private final String UniqueUUId;

    public CookieFactory(String username, String password, String uniqueUUId){
        this.password = password;
        this.username = username;
        this.UniqueUUId = uniqueUUId;
    }

    public String genCookieValue(){
        String builder = username +":"+ password +":"+ UniqueUUId;
        return Base64.getUrlEncoder().encodeToString(builder.getBytes(StandardCharsets.UTF_8));
    }

    public static String[] decodeCookieValue(String Base64Encode)
    {
        return new String(Base64.getUrlDecoder().decode(Base64Encode),StandardCharsets.UTF_8).split(":");
    }



}
