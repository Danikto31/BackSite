package org.example.backbase.Repository;

import org.example.backbase.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Методы для поиска клиентов по имени пользователя (если нужно)
    Client findByUsername(String username);
}