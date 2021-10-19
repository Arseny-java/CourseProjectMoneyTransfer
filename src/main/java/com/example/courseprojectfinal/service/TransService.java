package com.example.courseprojectfinal.service;


import com.example.courseprojectfinal.exeption.InvalidTransaction;
import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.Transaction;
import com.example.courseprojectfinal.model.ValidTransaction;
import com.example.courseprojectfinal.repository.TransRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TransService {
    private final TransRepository repository;
    private static final Logger logger = LoggerFactory.getLogger("transactionsLogger");

    public TransService(TransRepository repository) {
        this.repository = repository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        String msg = String.format("CardFrom = %s, CardTo = %s, Amount = %s", transaction.getCardFrom(),
                transaction.getCardTo(), transaction.getAmount());
        logger.info(msg);
        return repository.saveTransaction(transaction);
    }

    public Operation validTransaction(ValidTransaction confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new InvalidTransaction("Verification code is empty.");
        }
        return repository.validTransaction(confirmOperation.getOperationId());
    }

}
