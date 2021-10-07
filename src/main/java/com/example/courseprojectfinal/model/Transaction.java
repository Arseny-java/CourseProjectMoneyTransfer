package com.example.courseprojectfinal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Transaction {


    private final Card cardFrom;
    private final Card cardTo;
    private final Amount amount;
    private final Operation operationId;

    @JsonCreator
    public Transaction(@JsonProperty("cardFromNumber") String checkCardNumber1,
                    @JsonProperty("cardFromValidTill") String validTill,
                    @JsonProperty("cardFromCVV") String CVV,
                    @JsonProperty("cardToNumber") String checkCardNumber2,
                    @JsonProperty("amount") Amount amount) {

        this.cardFrom = new Card(checkCardNumber1, validTill, CVV);
        this.cardTo = new Card(checkCardNumber2);
        this.amount = amount;
        this.operationId = new Operation(Operation.generationCode());

    }


    public Card getCardFrom() {
        return cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public Amount getAmount() {
        return amount;
    }

    public Operation getOperationId() {
        return operationId;
    }
}
