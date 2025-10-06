package org.example.studentapi.repository;

import org.example.studentapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Account, Long> {
    Account findAccountByPhone(String phone);
}
