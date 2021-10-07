package model;

import com.example.courseprojectfinal.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransactionTest {
    String cardFromNumber = "6666666666666666";
    String cardFromValidTill = "12/34";
    String cardFromCVV = "666";
    String cardToNumber = "7777777777777777";
    int value = 1000;
    String currency = "RUR";


    @Test
    public void testDeSerializingWithJsonCreatorTransfer() throws IOException {

        String jsonString = String.format("{\"cardFromNumber\": \"%s\", \"cardFromValidTill\": \"%s\", \"cardFromCVV\": \"%s\", " +
                        "\"cardToNumber\":  \"%s\", \"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
                cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);

        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = mapper.readValue(jsonString, Transaction.class);

        assertThat(transaction.getCardFrom().getNumber(), equalTo(cardFromNumber));
        assertThat(transaction.getCardFrom().getValidTill(), equalTo(cardFromValidTill));
        assertThat(transaction.getCardFrom().getCardCVV(), equalTo(cardFromCVV));
        assertThat(transaction.getCardTo().getNumber(), equalTo(cardToNumber));
        assertThat(transaction.getAmount().getValue()*100, equalTo(value));
        assertThat((transaction.getAmount().getCurrency()), equalTo(currency));
    }

}
