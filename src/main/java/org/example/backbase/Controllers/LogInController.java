package org.example.backbase.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backbase.Entity.LoginBody;
import org.example.backbase.Services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
public class LogInController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("login")
    public boolean loginUser(@RequestBody LoginBody loginBody){
        return buyerService.getByUsernameAndPassword(loginBody.getUsername(), loginBody.getPassword()) != null;
    }


}
