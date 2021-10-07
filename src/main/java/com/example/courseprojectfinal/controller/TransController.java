package com.example.courseprojectfinal.controller;


import com.example.courseprojectfinal.exeption.InvalidCredentials;
import com.example.courseprojectfinal.exeption.InvalidTransaction;

import com.example.courseprojectfinal.model.ValidTransaction;
import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.Transaction;
import com.example.courseprojectfinal.service.TransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TransController {
    private static final Logger logger = LoggerFactory.getLogger("transactionsLogger");

    @Autowired
    TransService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public Operation save(@RequestBody Transaction transaction) {
        Transaction sendTransfer = service.saveTransaction(transaction);
        String msg = String.format("CardFrom = %s, CardTo = %s, Amount = %s", transaction.getCardFrom(),
                transaction.getCardTo(), transaction.getAmount());
        logger.info(msg);
        return sendTransfer.getOperationId();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/confirmOperation")
    public Operation confirm(@RequestBody ValidTransaction confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new InvalidTransaction("Verification code is empty.");
        }
        return service.validTransaction(confirmOperation.getOperationId());
    }


    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> errorInputDataHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTransaction.class)
    public ResponseEntity<String> errorTransferHandler(InvalidTransaction e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

