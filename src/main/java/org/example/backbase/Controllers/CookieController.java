package org.example.backbase.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue String cookieValue){
        return cookieValue;
    }

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("kukich", "cookieValue");
        cookie.setMaxAge(7 * 24 * 60 * 60); // Срок действия куки - 7 дней
        cookie.setHttpOnly(true); // Устанавливаем флаг HttpOnly
        cookie.setPath("/"); // Доступна для всех путей
        response.addCookie(cookie);
        return "Cookie set!";
    }

    @GetMapping("/delete-cookie")
    public String deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("kukich", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Cookie deleted!";
    }

}
