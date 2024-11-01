package org.example.backbase.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    private final List<String> listOfCookies = new ArrayList<>();
    private final String CookieName = "kukich";

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue String cookieValue){
        return cookieValue;
    }

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(CookieName, UUID.randomUUID().toString());
        cookie.setMaxAge(7 * 24 * 60 * 60); // Срок действия куки - 7 дней
        cookie.setHttpOnly(true); // Устанавливаем флаг HttpOnly
        cookie.setPath("/"); // Доступна для всех путей
        response.addCookie(cookie);
        listOfCookies.add(cookie.getValue());
        return "Cookie set!";
    }

    @GetMapping("/delete-cookie")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request) {
        Arrays.stream(request.getCookies()).filter(p->p.getName().equals(CookieName)).forEach(c->listOfCookies.remove(c.getValue()));
        Cookie cookie = new Cookie(CookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Cookie deleted!";
    }

    @GetMapping("/getCookieList")
    public List<String> getListOfCookies(){
        return listOfCookies;
    }

}
