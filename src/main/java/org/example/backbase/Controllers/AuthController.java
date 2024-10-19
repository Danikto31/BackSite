package org.example.backbase.Controllers;

import org.example.backbase.Entity.Client;
import org.example.backbase.Services.ClientService;
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
        // Проверьте, существует ли пользователь с таким именем
        if (clientService.findByUsername(client.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Пользователь уже существует");
        }

        // Сохраняем пользователя
        clientService.saveClient(client.getUsername(), client.getPassword());
        return ResponseEntity.ok("Регистрация прошла успешно");
    }
}