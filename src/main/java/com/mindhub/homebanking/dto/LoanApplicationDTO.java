package com.mindhub.homebanking.dto;

public class LoanApplicationDTO {
    private int payments;

    private double amount;

    private long idLoan;

    private String destinationAccount;


    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(int payments, double amount, long idLoan, String destinationAccount) {
        this.payments = payments;
        this.amount = amount;
        this.idLoan = idLoan;
        this.destinationAccount = destinationAccount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }


}
