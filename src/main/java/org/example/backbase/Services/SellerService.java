package org.example.backbase.Services;

import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Entity.SellerClient;
import org.example.backbase.Repository.BuyerRepository;
import org.example.backbase.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public SellerClient saveClient(String username, String password, String email, String fullName, String phoneNumber, String otherData) {
        SellerClient sellerClient = new SellerClient(username, password, email, fullName, phoneNumber, otherData);
        return sellerRepository.save(sellerClient);
    }

    public SellerClient findByUsername(String username) {
        return sellerRepository.findByUsername(username);
    }

}
