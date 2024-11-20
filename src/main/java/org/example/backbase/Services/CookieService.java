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

    public void saveCookie(String cookie, long userId){
        CookieClient cookieClient= new CookieClient(cookie, userId);
        cookieRepository.save(cookieClient);
    }

    public CookieClient getCookieByUserId(long id){
        return cookieRepository.getCookieClientByUserId(id);
    }

    public CookieClient getCookieClientByCookie(String cookie){
        return cookieRepository.getCookieClientByCookie(cookie);
    }



    public void deleteByCookie(String cookie){
        cookieRepository.deleteByCookie(cookie);
    }

    public void deleteById(long id){
        cookieRepository.deleteById(id);
    }

}
