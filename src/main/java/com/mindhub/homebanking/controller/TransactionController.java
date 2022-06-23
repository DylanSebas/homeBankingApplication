package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repository.AccountRepository;
import com.mindhub.homebanking.repository.ClientRepository;
import com.mindhub.homebanking.repository.TransactionRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.Utils.utils.getRandomNumber;
import static java.util.stream.Collectors.toList;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Transactional
    @PostMapping( "/api/transactions")
    public ResponseEntity<Object> transactionNew(
            @RequestParam Double amount, @RequestParam String description,
            Authentication authentication, @RequestParam String sourceNumber, @RequestParam String numberToDestination
    ) {


        Account rootAccount = accountService.getAccountFindByNumber(sourceNumber);

        Account recipientAccount = accountService.getAccountFindByNumber(numberToDestination);

        Client clientAuthentication=  clientService.getClientCurrent(authentication);

        List<String> numberAccountAuthenticado=clientAuthentication.getAccounts().stream().map(Account::getNumber).collect(toList());


        if (rootAccount == null ||  recipientAccount== null) {
            return new ResponseEntity<>("account does not exist", HttpStatus.FORBIDDEN);
        }

        if (amount.toString().isEmpty() || description.isEmpty() || sourceNumber.isEmpty() || numberToDestination.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }


        if(rootAccount.getBalance() < amount){
            return new ResponseEntity<>("insufficient amount", HttpStatus.FORBIDDEN);
        }



        if(!numberAccountAuthenticado.contains(sourceNumber)){
            return new ResponseEntity<>("your account does not exist", HttpStatus.FORBIDDEN);
        }


        if (sourceNumber.equals(numberToDestination)) {
            return new ResponseEntity<>("number of accounts are the same", HttpStatus.FORBIDDEN);
        }



        Transaction originTransaction=new Transaction(-amount, description+ rootAccount.getNumber(), LocalDateTime.now(), TransactionType.DEBITO, rootAccount, rootAccount.getBalance()-amount);
        Transaction destinationTransaction=new Transaction(amount, description+recipientAccount.getNumber(), LocalDateTime.now(), TransactionType.CREDITO, recipientAccount,recipientAccount.getBalance()+amount);

        rootAccount.setBalance(rootAccount.getBalance()-amount);
        recipientAccount.setBalance(recipientAccount.getBalance()+amount);

        transactionService.saveTransaction(originTransaction);
        transactionService.saveTransaction(destinationTransaction);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
