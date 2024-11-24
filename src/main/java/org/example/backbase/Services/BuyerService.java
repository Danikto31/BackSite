package org.example.backbase.Services;

import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Entity.LoginBody;
import org.example.backbase.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerClient saveClient(String username, String password, String email, String fullName, String phoneNumber) {
        BuyerClient buyerClient = new BuyerClient(username, password, email, fullName, phoneNumber);
        return buyerRepository.save(buyerClient);
    }

    public BuyerClient saveClient(BuyerClient buyerClient) {
        return buyerRepository.save(buyerClient);
    }

    public BuyerClient findByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }



    public BuyerClient getByUsernameAndPassword(String username, String password){
        return buyerRepository.getByUsernameAndPassword(username,password);
    }

    @Nullable
    public BuyerClient getClientByLoginBody(LoginBody loginBody) {
        return buyerRepository.getByUsernameAndPassword(loginBody.getUsername(), loginBody.getPassword());
    }

}