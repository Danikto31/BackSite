package org.example.backbase.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Request;
import org.example.backbase.Entity.BuyerClient;
import org.example.backbase.Entity.SellerClient;
import org.example.backbase.Services.BuyerService;
import org.example.backbase.Services.SellerService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/registerClient")
    public void registerClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Writer writer = response.getWriter(); BufferedReader reader = new BufferedReader(new FileReader("../resources/userForm.html"))) {
            String html = reader.lines().collect(Collectors.joining("\n"));
            if (request.getAttribute("isSeller") != null) {
                html = html.replace("/registerClient", "/registerClient?needUpgrade=true")
                        .replace("<button>Register</button>", "<button>Register seller</button>");
            }
            writer.write(html);
        }
    }

    @PostMapping("/registerClient")
    public void registerClient(HttpServletResponse response, HttpServletRequest request, @RequestBody BuyerClient buyerClient) throws IOException {
        try {
            // Сохраняем пользователя
            buyerService.saveClient(buyerClient.getUsername(), buyerClient.getPassword(), buyerClient.getEmail(), buyerClient.getFullName(), buyerClient.getPhoneNumber());
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Регистрация клиента прошла успешно");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().println("Пользователь(Клиент) уже существует");
            return;
        }
        if (request.getAttribute("needUpgrade") != null) response.sendRedirect("/upgradeToSeller");
    }

    @GetMapping("/upgradeToSeller")
    public void upgradeToSeller(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (FileReader reader = new FileReader("../resources/sellerForm.html")) {
            reader.transferTo(response.getWriter());
        }
    }

    @PostMapping("/upgradeToSeller")
    public ResponseEntity<String> upgradeToSeller(@RequestBody SellerClient sellerClient){
        try {
            sellerService.saveClient(sellerClient.getUsername(), sellerClient.getPassword(), sellerClient.getEmail(), sellerClient.getFullName(), sellerClient.getPhoneNumber(), sellerClient.getOtherData());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Пользователь(Продавец) уже существует");
        }
        return ResponseEntity.ok("Регистрия продавца прошла успешно");
    }

    @PostMapping
    public boolean checkClient(@RequestBody String name){
        return buyerService.findByUsername(name) != null;
    }

}