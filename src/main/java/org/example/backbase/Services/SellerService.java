package org.example.backbase.Services;

import org.example.backbase.Entity.SellerClient;
import org.example.backbase.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public SellerClient saveClient(SellerClient sellerClient) {
        return sellerRepository.save(sellerClient);
    }

    public SellerClient findByUsername(String username) {
        return sellerRepository.findByUsername(username);
    }

}
