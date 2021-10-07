package model;

import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.ValidTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ValidTransactionTest {
    @Test
    public void deSerializingValidTransactionTest() throws IOException {
        String jsonString = "{\"operationId\": \"666\", \"code\": \"RUR\"}";
        ObjectMapper mapper = new ObjectMapper();
        ValidTransaction example = mapper.readValue(jsonString, ValidTransaction.class);
        Operation operation = example.getOperationId();
        assertThat(operation.getOperationId(), equalTo("666"));
        assertThat(example.getCode(), equalTo("RUR"));
    }
}
