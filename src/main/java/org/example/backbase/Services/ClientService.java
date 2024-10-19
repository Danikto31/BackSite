package org.example.backbase.Services;

import org.example.backbase.Entity.Client;
import org.example.backbase.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(String username, String password) {
        Client client = new Client(username, password);
        return clientRepository.save(client);
    }

    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}