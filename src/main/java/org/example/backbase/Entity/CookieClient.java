package org.example.backbase.Entity;

import jakarta.persistence.*;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name = "cookie")
public class CookieClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cookie", unique = true)
    private Cookie cookie;

    @Column(unique = true)
    private String username;

    public CookieClient(Cookie cookie){
        this.cookie = cookie;
    }

    public CookieClient(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
