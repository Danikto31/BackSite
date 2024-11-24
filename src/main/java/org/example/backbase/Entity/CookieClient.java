package org.example.backbase.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cookie")
public class CookieClient {

    @Id
    @Column(unique = true)
    private String cookie;

    @Column(unique = true)
    private long userId;

    //TODO: Добавить поле isSeller

    public CookieClient(String cookie, long userId){
        this.cookie = cookie;
        this.userId = userId;
    }

    public CookieClient(){}

    public Long getId() {
        return userId;
    }


    public String getCookie() {
        return cookie;
    }
}
