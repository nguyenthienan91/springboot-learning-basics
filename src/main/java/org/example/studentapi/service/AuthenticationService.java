package org.example.studentapi.service;

import org.example.studentapi.entity.Account;
import org.example.studentapi.model.request.LoginRequest;
import org.example.studentapi.model.response.AccountResponse;
import org.example.studentapi.repository.AuthenticationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    //xử lý logic do controller đưa qua

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenService tokenService;

    public Account register(Account account){
        // Xử lý logic cho register
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        // lưu dô DB
        return authenticationRepository.save(account);
    }

    public AccountResponse login(LoginRequest loginRequest) {
        // xử lí logic xác thực tài khoản
        Authentication authentication = null;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getPhone(), loginRequest.getPassword()));
        Account account = (Account) authentication.getPrincipal();
        AccountResponse acountReponse = modelMapper.map(account, AccountResponse.class);
        String token = tokenService.generateToken(account);
        acountReponse.setToken(token);
        return acountReponse;
    }

    public List<Account> getAllAccount(){
        List<Account> accounts =authenticationRepository.findAll();
        return accounts;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return authenticationRepository.findAccountByPhone(phone);
    }

    public Account getCurrentAccount(){
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // cơ chế :
    // B1: Lấy user name người dùng nhập
    // B2: Tìm trong DB xem có account nào trùng với username đó không (loadUserByUsername)
    // B3: authenticationManager => compare tk password db <=> password người dùng nhập

}
