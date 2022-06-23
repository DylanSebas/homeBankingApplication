package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private String firstName;
    private String lastName;
    private String email;
    private long id;
    private Set<AccountDTO> accounts = new HashSet<>();
    private Set<ClientLoanDTO> loan=new HashSet<>();
    private Set<CardDTO> cards=new HashSet<>();
    
    public ClientDTO(){};

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts= client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loan=client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.cards=client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }

    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ClientLoanDTO> getLoan() {
        return loan;
    }

    public void setLoan(Set<ClientLoanDTO> loan) {
        this.loan = loan;
    }
}