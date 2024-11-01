package org.example.backbase.Services;

import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public BuyerClient saveClient(String username, String password) {
        BuyerClient buyerClient = new BuyerClient(username, password);
        return clientRepository.save(buyerClient);
    }

    public BuyerClient findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}