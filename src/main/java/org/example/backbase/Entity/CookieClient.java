package org.example.backbase.Entity;

import jakarta.persistence.*;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name = "cookie")
public class CookieClient {

    @Id
    @Column(unique = true)
    private Cookie cookie;

    @Column(unique = true)
    private long user_id;

    public CookieClient(Cookie cookie, long userId){
        this.cookie = cookie;
        this.user_id = userId;
    }

    public CookieClient(){}

    public Long getId() {
        return user_id;
    }


    public Cookie getCookie() {
        return cookie;
    }
}
