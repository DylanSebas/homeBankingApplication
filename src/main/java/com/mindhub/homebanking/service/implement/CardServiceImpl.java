package com.mindhub.homebanking.service.implement;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repository.CardRepository;
import com.mindhub.homebanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public void saveCard(Card card) {cardRepository.save(card);}

    @Override
    public void deleteCard(long id) {
        cardRepository.findById(id).ifPresent(card -> cardRepository.delete(card));

    }
}
