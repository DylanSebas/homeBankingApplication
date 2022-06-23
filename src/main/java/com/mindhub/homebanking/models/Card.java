package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String cardHolder;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;
    private int cvv;
    private String number;
    private CardColorType colorType;

    private CardType typeCard;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    public Card() {
    }

    public Card(Client cardHolder,CardType cardType , LocalDateTime fromDate, LocalDateTime thruDate, int cvv, String number,Client client, CardColorType colorType) {
        this.client=client;
        this.cardHolder = cardHolder.getFirstName()+" "+cardHolder.getLastName();
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.cvv = cvv;
        this.number = number;
        this.colorType=colorType;
        this.typeCard=cardType;
    }

    public CardType getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(CardType typeCard) {
        this.typeCard = typeCard;
    }

    public CardColorType getColorType() {
        return colorType;
    }

    public void setColorType(CardColorType colorType) {
        this.colorType = colorType;
    }

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(Client cardHolder) {
        this.cardHolder = String.valueOf(cardHolder);
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
