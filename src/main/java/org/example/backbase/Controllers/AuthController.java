package org.example.backbase.Controllers;

import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Entity.SellerClient;
import org.example.backbase.Services.BuyerService;
import org.example.backbase.Services.SellerService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/registerClient")
    public ResponseEntity<String> registerClient(@RequestBody BuyerClient buyerClient) {
        try {
            // Сохраняем пользователя
            buyerService.saveClient(buyerClient.getUsername(), buyerClient.getPassword(), buyerClient.getEmail(), buyerClient.getFullName(), buyerClient.getPhoneNumber());
            return ResponseEntity.ok("Регистрация клиента прошла успешно");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Пользователь(Клиент) уже существует");
        }
    }

    @PostMapping("/registerSeller")
    public ResponseEntity<String> registerSeller(@RequestBody SellerClient sellerClient){
        try {
            if(buyerService.findByUsername(sellerClient.getUsername())==null)return ResponseEntity.badRequest().body("Пользователь не зарегестрирован как покупатель");
            sellerService.saveClient(sellerClient.getUsername(),sellerClient.getPassword(), sellerClient.getEmail(), sellerClient.getFullName(), sellerClient.getPhoneNumber(), sellerClient.getOtherData());
            return ResponseEntity.ok("Регистрия продавца прошла успешно");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Пользователь(Продавец) уже существует");
        }
    }

    @PostMapping
    public boolean checkClient(@RequestBody String name){
        return buyerService.findByUsername(name) != null;
    }

}