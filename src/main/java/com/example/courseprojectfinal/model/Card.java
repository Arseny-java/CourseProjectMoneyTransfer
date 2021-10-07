package com.example.courseprojectfinal.model;



import com.example.courseprojectfinal.exeption.InvalidCredentials;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class Card {

    private String number;
    private String validTill;
    private String cardCVV;
    private final String forCardTo = "not indicated";


    public Card(String number, String validTill, String cardCVV) {
        if (checkNumber1(number) && checkNumber2(number)) {
            this.number = number;
        }
        if (checkValidTill(validTill)) {
            this.validTill = validTill;
        }
        if (checkCardCVV(cardCVV)) {
            this.cardCVV = cardCVV;
        }

    }

    public Card() {
    }

    public Card(String number) {
        if (checkNumber1(number) && checkNumber2(number)) {
            this.number = number;
        }
        this.validTill = forCardTo;
        this.cardCVV = forCardTo;

    }

    public boolean checkNumber1(String number) {
        //если пусто
        if (isEmpty(number)) {
            throw new InvalidCredentials("Поле \"Номер карты\" не заполнено!");
        }
        return true;
    }

    public boolean checkNumber2(String number) {
        // минимум 16 знаков
        if (number.length() != 16) {
            throw new InvalidCredentials("Поле \"Номер карты\" содержит меньше 16 знаков!");
        }
        return true;
    }

    public boolean checkValidTill(String validTill) {
        //если пусто
        if (isEmpty(validTill)) {
            throw new InvalidCredentials("Поле \"MM / YY\" не заполнено!");
        }
        //минимум 4 знака
        if (validTill.length() != 5) {
            throw new InvalidCredentials("Поле \"MM / YY\" содержит недопустимое количество знаков!");
        }
        YearMonth lastValidMonth = parse(validTill);
        if (YearMonth.now(ZoneOffset.UTC).isAfter(lastValidMonth)) {
            throw new InvalidCredentials("Срок действия карты истек");
        }
        //месяц не может быть ниже 1
        //месяц не может быть выше 12
        int month = lastValidMonth.getMonthValue();
        if (month < 1 || month > 12) {
            throw new InvalidCredentials("Поле \"MM / YY\" не может содержать номер месяца меньще 0 и больше 12");
        }
        return true;
    }


    //parse
    public YearMonth parse(String validTill) {
        try {
            DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
            //получает экземпляр YearMonth из текстовой строки с помощью специального средства форматирования.
            return YearMonth.parse(validTill, ccMonthFormatter);
        } catch (DateTimeException ex) {
            throw new InvalidCredentials("Ошибка парсинга Даты");
        }

    }

    public boolean checkCardCVV(String cardCVV) {
        //если пусто
        if (isEmpty(cardCVV)) {
            throw new InvalidCredentials("Поле \"CVV\" не заполнено!");
        }
        // минимум 3 знака
        if (cardCVV.length() != 3) {
            throw new InvalidCredentials("Поле \"CVV\"содержит недопустимое количество знаков!");
        }
        return true;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public String getNumber() {
        return number;
    }

    public String getValidTill() {
        return validTill;
    }

    public String getCardCVV() {
        return cardCVV;
    }


    @Override
    public String toString() {
        if (validTill.equals(forCardTo) && cardCVV.equals(forCardTo)) {
            return "Card{" +
                    "number='" + number + '\'' +
                    '}';
        }
        return "Card{" +
                "number='" + number + '\'' +
                ", validTill='" + validTill + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                '}';

    }
}





