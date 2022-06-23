package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.*;
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

import static java.util.stream.Collectors.toList;

@Transactional
@RestController
public class LoansController {
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("api/loans")
    public List<LoanDTO> getloans(){
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());
    }


    @PostMapping("api/loans")
    public ResponseEntity<Object> loanRequest(
            @RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

        Client clientAuthentication=  clientService.getClientCurrent(authentication);

        Loan loan=loanRepository.findById(loanApplicationDTO.getIdLoan()).orElse(null);

        Account recipientAccount = accountService.getAccountFindByNumber(loanApplicationDTO.getDestinationAccount());



        if (loanApplicationDTO.getAmount()<0 || loanApplicationDTO.getPayments()<0 ) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if(loan== null ){
            return new ResponseEntity<>("this loan does not exist", HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("The amount requested is greater than the amount allowed", HttpStatus.FORBIDDEN);
        }


        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("The amount of payments is not alloweb in this type of loan", HttpStatus.FORBIDDEN);
        }

        if(!clientAuthentication.getAccounts().stream().map(Account::getNumber).collect(toList()).contains(loanApplicationDTO.getDestinationAccount())){
            return new ResponseEntity<>("the accounts do not exist", HttpStatus.FORBIDDEN);
        }

        Double valueWithInterest = loanApplicationDTO.getAmount()+ (20 / 100.0) * loanApplicationDTO.getAmount();


        Transaction destinationTransaction=new Transaction(loanApplicationDTO.getAmount(), "loan approved "+loan.getName(), LocalDateTime.now(), TransactionType.CREDITO, recipientAccount,recipientAccount.getBalance()+loanApplicationDTO.getAmount());

        ClientLoan clientLoanOne=   new ClientLoan(valueWithInterest.intValue(),loanApplicationDTO.getPayments(),clientAuthentication, loan);

        clientLoanRepository.save(clientLoanOne);

        transactionService.saveTransaction(destinationTransaction);

        recipientAccount.setBalance(loanApplicationDTO.getAmount()+ recipientAccount.getBalance());

        accountService.saveAccount(recipientAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
