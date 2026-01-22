package com.zyad.social_platform_api.service;

import org.springframework.stereotype.Service;

import com.zyad.social_platform_api.entity.Account;
import com.zyad.social_platform_api.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;
    public AccountService(AccountRepository ar){
        this.accountRepository = ar;
    }

    public Account newUserService(Account accountToAdd){
        return accountRepository.save(accountToAdd);
    }

    public Account user(String username){
        return accountRepository.findByUsername(username);
    }

    public Account login(String username, String password){
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public boolean findID(int id){
        return accountRepository.existsById(id);
    }
}
