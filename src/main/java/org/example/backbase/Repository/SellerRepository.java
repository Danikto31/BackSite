package org.example.backbase.Repository;

import org.example.backbase.Entity.SellerClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerClient,Long> {
    SellerClient findByUsername(String username);

}
