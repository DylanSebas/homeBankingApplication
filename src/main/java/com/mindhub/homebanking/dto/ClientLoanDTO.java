package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ClientLoanDTO {

    private long id;
    private long idLoan;
    private String name;
    private int payments;

    private int amount;


    public ClientLoanDTO() {
    }


    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id= clientLoan.getId();
        this.idLoan=clientLoan.getLoan().getId();
        this.name=clientLoan.getLoan().getName();
        this.amount=clientLoan.getAmount();
        this.payments= clientLoan.getPayments();
    }

    public long getId() {
        return id;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
