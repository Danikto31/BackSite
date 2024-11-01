package org.example.backbase.Services;

import jakarta.servlet.http.Cookie;
import org.example.backbase.Entity.CookieClient;
import org.example.backbase.Repository.CookieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Autowired
    CookieRepository cookieRepository;

    public CookieClient saveCookie(Cookie cookie){
        CookieClient cookieClient= new CookieClient(cookie);
        return cookieRepository.save(cookieClient);
    }

    public CookieClient getCookieById(Long id){
        return cookieRepository.getCookieClientById(id);
    }
}
