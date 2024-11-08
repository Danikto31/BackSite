package org.example.backbase.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backbase.Entity.CookieClient;
import org.example.backbase.Entity.LoginBody;
import org.example.backbase.Services.BuyerService;
import org.example.backbase.Services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LogInController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private CookieService cookieService;

    private CookieClient cookieClient;

    private static Cookie cookie;

    public static boolean checkCookie(Cookie[] cookies){
        for(Cookie c:cookies){
            if(c.getName().equals(CookieController.getCookieName())){
                cookie = c;
                return true;
            }
        }
        return false;
    }

   /* @GetMapping("/checkCookie")
    public boolean checkCookie(HttpServletRequest request)
    {
        if(CookieController.checkCookie(request.getCookies()))
        {
            try {

            }
        }
    }*/

    @GetMapping("/checkCookieLogin")
    public LoginBody checkCookieLogin(HttpServletRequest request){
    return null;
    }

    @GetMapping("/login")
    public boolean loginUser(@RequestBody LoginBody loginBody)
    {
        return buyerService.getByUsernameAndPassword(loginBody.getUsername(), loginBody.getPassword()) != null;
    }


}
