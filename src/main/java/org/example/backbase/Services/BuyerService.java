package org.example.backbase.Services;

import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerClient saveClient(String username, String password) {
        BuyerClient buyerClient = new BuyerClient(username, password);
        return buyerRepository.save(buyerClient);
    }

    public BuyerClient findByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }

    public BuyerClient getByUsernameAndPassword(String username, String password){
        return buyerRepository.getByUsernameAndPassword(username,password);
    }
}