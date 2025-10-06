package org.example.studentapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.studentapi.entity.Account;
import org.example.studentapi.model.request.LoginRequest;
import org.example.studentapi.model.response.AccountResponse;
import org.example.studentapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name="api")
public class AuthenticationController {
    // S.O.L.I.D
    // điều hướng (controller) => xử lý logic (service) => lưu DB (repository) (JPA)
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/api/account")
    public ResponseEntity register(@Valid @RequestBody Account account){
        // nhận yêu cầu từ FE
        // => đẩy qua AuthenticationService

        Account newAccount = authenticationService.register(account);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("api/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        AccountResponse account = authenticationService.login(loginRequest);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/api/account")
    public ResponseEntity getAllAccount(){
        List<Account> accounts = authenticationService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/api/account/current")
    public ResponseEntity getCurrentAccount(){
        Account account = authenticationService.getCurrentAccount();
        return ResponseEntity.ok(account);
    }



}
