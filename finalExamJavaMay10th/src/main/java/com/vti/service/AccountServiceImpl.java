package com.vti.service;

import com.vti.Specification.AccountSpecification;
import com.vti.entity.Account;
import com.vti.form.Account.AccountFilterForm;
import com.vti.form.Account.CreateAccount;
import com.vti.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service("accountService")
@Transactional(rollbackOn = Exception.class)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository; //null new AccountRepository

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<Account> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAll(pageable); // nullPoint
    }

    @Override
    public Account createAccount(CreateAccount form) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(form, Account.class);
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account updateAccount(int id, CreateAccount form) {
        ModelMapper modelMapper = new ModelMapper();
        boolean check = accountRepository.existsById(id);
        if (check) {
            Account account = accountRepository.getById(id);
            modelMapper.map(form, account);
            accountRepository.save(account);
            return account;
        }
        return null;
    }

    @Override
    public String deleteAccount(int id) {

            accountRepository.deleteById(id);
            if (accountRepository.existsById(id)) {
                return "delete acc fail";
            }
            return "delete acc success";
    }

    @Override
    public boolean isUsernameExists(String username) {
        boolean check = accountRepository.existsByUsername(username);
        if (check) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Account finBySpeci(AccountFilterForm form) {
        Specification<Account> accountSpecification = AccountSpecification.buildWhere(form);
        return (Account) accountSpecification;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("");
        } else {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        }
    }
}
