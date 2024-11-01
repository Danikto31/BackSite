package org.example.backbase.Repository;

import jakarta.servlet.http.Cookie;
import org.example.backbase.Entity.CookieClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<CookieClient,Long> {

    CookieClient getCookieClientById(Long id);

}
