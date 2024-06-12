package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.Account.AccountFilterForm;
import com.vti.form.Account.CreateAccount;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface AccountService extends UserDetailsService {
    boolean isUsernameExists(String username);
    // read
    Account finBySpeci(AccountFilterForm form);
    Page<Account> findAll(int page, int size);
    // create
    Account createAccount(CreateAccount form);
    // update
    Account updateAccount(int id, CreateAccount form);
    // delete
    String deleteAccount(int id);
}
