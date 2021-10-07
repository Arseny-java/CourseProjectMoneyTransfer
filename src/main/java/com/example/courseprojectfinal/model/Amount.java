package com.example.courseprojectfinal.model;


import com.example.courseprojectfinal.exeption.InvalidCredentials;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Amount {

    private int value;
    private String currency;
    private double com;
    double proc = 0.01;

    @JsonCreator
    public Amount(@JsonProperty("value") Integer value,
                  @JsonProperty("currency") String currency) {
        if (checkValue(value)) {
            this.value = value / 100;
        }
        this.currency = currency;

        this.com = value * proc;
    }

    public Amount() {
    }

    public boolean checkValue(Integer value) {
        //если пусто
        if (isEmpty(value)) {
            throw new InvalidCredentials("Поле \"Сумма перевода\" не заполнено.");
        }
        // не может быть равное или меньше 0
        if (value <= 0) {
            throw new InvalidCredentials("Поле \"Сумма перевода\" не может содержать 0 или быть меньше 0.");
        }
        return true;
    }

    public boolean isEmpty(Integer value) {
        return value == null;
    }

    public int getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {

        return "Amount{" +
                "Сумма = " + value +
                ", Валюта = '" + currency +
                ", Комиссия = " +
                com * proc +
                '}';
    }
}


