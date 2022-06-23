package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Card;
import org.springframework.beans.factory.annotation.Autowired;

public interface CardService {

    @Autowired
    void saveCard(Card card);
    @Autowired
    void deleteCard(long card);
}
