package org.example.backbase.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backbase.Entity.LoginBody;
import org.example.backbase.Services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    @Autowired
    private CookieService cookieService;

    private CookieFactory cookieFactory;

    private static final String CookieName = "kukich";



    public static String getCookieName() {
        return CookieName;
    }


    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue String cookieValue){
        return cookieValue;
    }

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response, @RequestBody LoginBody loginBody) {
        cookieFactory = new CookieFactory(loginBody.getUsername(),loginBody.getPassword(),UUID.randomUUID().toString());
        Cookie cookie = new Cookie(CookieName, cookieFactory.genCookieValue());
        cookie.setMaxAge(7 * 24 * 60 * 60); // Срок действия куки - 7 дней
        cookie.setHttpOnly(true); // Устанавливаем флаг HttpOnly
        cookie.setPath("/"); // Доступна для всех путей
        response.addCookie(cookie);
        cookieService.saveCookie(cookie);
        return "Cookie set!" + "\n" + Arrays.toString(CookieFactory.decodeCookieValue(cookieFactory.genCookieValue()));
    }

    @GetMapping("/delete-cookie")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request) {
        Arrays.stream(request.getCookies()).filter(c->c.getName().equals(CookieName)).forEach(c->cookieService.deleteByCookie(c));
        Cookie cookie = new Cookie(CookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Cookie deleted!";
    }

    @GetMapping("/get-cookie-value")
    public String getCookieValue(@RequestParam Long id){
        return cookieService.getCookieById(id).getCookie().getValue();
    }

    @PostMapping("/delete-cookie-id")
    public void removeCookieById(@RequestParam Long id){
        cookieService.deleteById(id);
    }
}
