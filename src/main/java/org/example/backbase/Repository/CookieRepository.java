package org.example.backbase.Repository;

import jakarta.servlet.http.Cookie;
import org.example.backbase.Entity.CookieClient;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<CookieClient,Long> {

    CookieClient getCookieClientByCookie(String cookie);

    CookieClient getCookieClientByUserId(Long id);

    void deleteById(Long id);

    void deleteByCookie(String cookie);
}
