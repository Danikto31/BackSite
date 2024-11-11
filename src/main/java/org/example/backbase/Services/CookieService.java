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

    public CookieClient saveCookie(Cookie cookie, long user_id){
        CookieClient cookieClient= new CookieClient(cookie, user_id);
        return cookieRepository.save(cookieClient);
    }

    public CookieClient getCookieById(Long id){
        return cookieRepository.getCookieClientById(id);
    }

    public CookieClient getCookieClientByCookie(Cookie cookie){
        return cookieRepository.getCookieClientByCookie(cookie);
    }



    public void deleteByCookie(Cookie cookie){
        cookieRepository.deleteByCookie(cookie);
    }

    public void deleteById(Long id){
        cookieRepository.deleteById(id);
    }

}
