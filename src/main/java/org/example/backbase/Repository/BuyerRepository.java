package org.example.backbase.Repository;

import org.example.backbase.Entity.BuyerClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerClient, Long> {

    // Методы для поиска клиентов по имени пользователя (если нужно)
    BuyerClient findByUsername(String username);

    BuyerClient getByUsernameAndPassword(String username, String password);

}