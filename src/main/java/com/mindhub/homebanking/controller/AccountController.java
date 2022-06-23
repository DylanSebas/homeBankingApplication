package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repository.AccountRepository;
import com.mindhub.homebanking.repository.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.Utils.utils.getRandomNumber;
import static java.util.stream.Collectors.toList;

@RestController @RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        return accountService.getAccountDto();
    }

    @GetMapping("accounts/{id}")
    public AccountDTO getAccount(@PathVariable long id){

        return accountService.getAccountDto(id);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> registerAccount(Authentication authentication) {

        Client clientAuthentication=  clientService.getClientCurrent(authentication);

        if(clientAuthentication.getAccounts().size()>3) {
            return new ResponseEntity<>("you already have 3 accounts", HttpStatus.FORBIDDEN);
        }


        accountService.saveAccount(new Account("VIN-"+getRandomNumber(10000000,99999999),LocalDateTime.now(),0.,clientAuthentication));


        return new ResponseEntity<>(HttpStatus.CREATED);

    }



}
