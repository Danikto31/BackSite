package org.example.backbase.Controllers;

import org.example.backbase.Entity.Client;
import org.example.backbase.Services.ClientService;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        try {
            // Сохраняем пользователя
            clientService.saveClient(client.getUsername(), client.getPassword());
            return ResponseEntity.ok("Регистрация прошла успешно");
        } catch (PSQLException e) {
            return ResponseEntity.badRequest().body("Пользователь уже существует");
        }
    }

    public boolean checkClient(@RequestBody String name){
        return clientService.findByUsername(name) != null;
    }

}