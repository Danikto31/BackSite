package org.example.backbase.Controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Entity.LoginBody;
import org.example.backbase.Entity.SellerClient;
import org.example.backbase.Services.BuyerService;
import org.example.backbase.Services.CookieService;
import org.example.backbase.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    @Autowired
    private CookieService cookieService;

    private CookieFactory cookieFactory;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    private static final String CookieName = "kukich";

    public static String getCookieName() {
        return CookieName;
    }




    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue String cookieValue){
        return cookieValue;
    }

    @GetMapping("/check-cookie")
    public String checkCookie(@RequestBody LoginBody loginBody)
    {
        BuyerClient buyerClient = buyerService.findByUsername(loginBody.getUsername());

        if(buyerClient!=null&&buyerClient.getPassword().equals(loginBody.getPassword())){

            SellerClient sellerClient = sellerService.findByUsername(loginBody.getUsername());

            if(sellerClient!=null&&sellerClient.getPassword().equals(loginBody.getPassword()))
            {
                return "Seller";
            }
        return "Buyer";
        }
        return null;
    }


    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response, @RequestBody LoginBody loginBody) {
        BuyerClient buyerClient = buyerService.getByUsernameAndPassword(loginBody.getUsername(), loginBody.getPassword());
        if(buyerClient!=null) {
            cookieFactory = new CookieFactory(buyerClient.getId().toString(),buyerClient.getUsername(), buyerClient.getPassword());
            Cookie cookie = new Cookie(CookieName, cookieFactory.genCookieValue());
            cookie.setMaxAge(7 * 24 * 60 * 60); // Срок действия куки - 7 дней
            cookie.setHttpOnly(true); // Устанавливаем флаг HttpOnly
            cookie.setPath("/"); // Доступна для всех путей
            response.addCookie(cookie);
            cookieService.saveCookie(cookie.getValue(), buyerClient.getId());
            return "Cookie set!" + "\n" + Arrays.toString(CookieFactory.decodeCookieValue(cookieFactory.genCookieValue()));
        }else {
            System.out.println("Username not found");
            return null;
        }
    }

    @GetMapping("/decode-cookie")
    public String[] decodeCookie(@RequestBody decodeCookie dcookie){
        return CookieFactory.decodeCookieValue(dcookie.getBase64Cookie());
    }



    public static class Username{

        public String getUsername() {
            return username;
        }

        @JsonProperty("username")
        public String username;

        public Username(){}

        public Username(String username){
            this.username = username;
        }
    }

    public static class decodeCookie{

        public String getBase64Cookie() {
            return base64Cookie;
        }

        @JsonProperty("cookie")
        private String base64Cookie;

        public decodeCookie(String base64Cookie){this.base64Cookie=base64Cookie;}

        public decodeCookie(){}

    }


}
