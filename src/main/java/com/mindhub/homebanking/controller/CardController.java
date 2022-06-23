package com.mindhub.homebanking.controller;


import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.CardRepository;
import com.mindhub.homebanking.repository.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.mindhub.homebanking.Utils.utils.getRandomNumber;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> registerCard(Authentication authentication , @RequestParam CardColorType  cardColorType, @RequestParam CardType cardType) {

        Client clientAuthentication=  clientService.getClientCurrent(authentication);

        if(clientAuthentication.getCards().stream().filter(e->e.getTypeCard().equals(CardType.DEBIT)).count()>3 || clientAuthentication.getCards().stream().filter(e->e.getTypeCard().equals(CardType.CREDIT)).count()>3) {
            return new ResponseEntity<>("you already have 3 card", HttpStatus.FORBIDDEN);
        }



        cardService.saveCard(new Card( clientAuthentication,cardType,LocalDateTime.now(),LocalDateTime.now().plusYears(5), getRandomNumber(100,999),getRandomNumber(1000,9999)+" "+getRandomNumber(1000,9999)+" "+getRandomNumber(1000,9999)+" "+getRandomNumber(1000,9999),clientAuthentication,cardColorType));


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/clients/current/cards/{id}")
    public ResponseEntity <Object> deleteCard ( @PathVariable long id){


        cardService.deleteCard(id);

        return new ResponseEntity<>("Card deleted",HttpStatus.OK);
    }

}
