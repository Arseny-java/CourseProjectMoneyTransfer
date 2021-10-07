package repositoryTest;

import com.example.courseprojectfinal.model.Amount;
import com.example.courseprojectfinal.model.Transaction;
import com.example.courseprojectfinal.repository.TransRepository;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TransRepositoryTest {
    Amount amount = new Amount(777, "RUR");
    Transaction transferActual = new Transaction("6666666666666666", "12/34", "666",
            "7777777777777777", amount);

    @Test
    public void testSaveTransfer() {

        List<Transaction> transRepository = new ArrayList<>();
        transRepository.add(transferActual);

        TransRepository transRepository1 = new TransRepository();
        transRepository1.saveTransaction(transferActual);

        assertEquals(transRepository.size(), transRepository1.size());
    }
}
