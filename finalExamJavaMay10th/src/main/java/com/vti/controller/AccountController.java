package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.Account.AccountFilterForm;
import com.vti.form.Account.CreateAccount;
import com.vti.repository.AccountRepository;
import com.vti.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/auth")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/findAllAcc")
        public ResponseEntity<List<AccountDTO>> findAll(@RequestParam int page, @RequestParam int size){
            Page<Account> accounts = accountService.findAll(page, size);
            List<AccountDTO> accountDTOS = modelMapper.map(accounts.getContent(), new TypeToken<List<AccountDTO>>(){}.getType());
            return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
        }


    @GetMapping("/findBySppec")
    public ResponseEntity<?> findBySppec(@RequestBody AccountFilterForm form){
        return new ResponseEntity<>(accountService.finBySpeci(form), HttpStatus.OK);
    }
    @PostMapping("/admin/createAcc")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createAccount( @RequestBody @Valid CreateAccount form){
        Account account = modelMapper.map(form, Account.class);
        accountRepository.save(account);
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return new ResponseEntity<>(accountDTO , HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateAcc")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAccount(@RequestParam int id, @RequestBody CreateAccount form){
        Account updatedAccount = accountService.updateAccount(id, form);
        if (updatedAccount != null) {
            AccountDTO accountDTO = modelMapper.map(updatedAccount, AccountDTO.class);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/deleteAcc")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteAccount(@RequestParam int id){
        return new ResponseEntity<>(accountService.deleteAccount(id) , HttpStatus.OK);
    }
}