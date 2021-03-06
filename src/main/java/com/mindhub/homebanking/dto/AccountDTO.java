package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;
    private Set<TransactionDTO> transactions = new HashSet<>();


    public AccountDTO(){}

    public AccountDTO(Account account){
        this.number=account.getNumber();
        this.balance=account.getBalance();
        this.creationDate=account.getCreationDate();
        this.id=account.getId();
        this.transactions= account.getTransaction().stream().map(TransactionDTO::new).collect(Collectors.toSet());

    }

    public long getId() {
        return id;
    }

    public Set<TransactionDTO> getTransaction() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
