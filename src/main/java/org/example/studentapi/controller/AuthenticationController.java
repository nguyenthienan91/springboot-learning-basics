package org.example.studentapi.controller;

import jakarta.validation.Valid;
import org.example.studentapi.entity.Account;
import org.example.studentapi.model.request.LoginRequest;
import org.example.studentapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {
    // S.O.L.I.D
    // điều hướng (controller) => xử lý logic (service) => lưu DB (repository) (JPA)
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/api/register")
    public ResponseEntity register(@Valid @RequestBody Account account){
        // nhận yêu cầu từ FE
        // => đẩy qua authenticationService

        Account newAccount = authenticationService.register(account);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        Account account = authenticationService.login(loginRequest);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/api/account")
    public ResponseEntity getAllAccount(){
        List<Account> accounts = authenticationService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }



}
