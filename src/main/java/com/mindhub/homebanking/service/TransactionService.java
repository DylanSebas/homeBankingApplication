package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public interface TransactionService {
    @Autowired
    void saveTransaction(Transaction transaction);

}
