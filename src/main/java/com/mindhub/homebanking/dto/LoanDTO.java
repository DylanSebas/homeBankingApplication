package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.stream.Collectors;

public class LoanDTO {

    private List<Integer> payments;

    private String name;

    private double maxAmount;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.payments = loan.getPayments();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
