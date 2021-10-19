package com.example.courseprojectfinal.controller;


import com.example.courseprojectfinal.exeption.InvalidCredentials;
import com.example.courseprojectfinal.exeption.InvalidTransaction;
import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.Transaction;
import com.example.courseprojectfinal.model.ValidTransaction;
import com.example.courseprojectfinal.service.TransService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TransController {

    private final TransService service;

    public TransController(TransService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public Operation save(@RequestBody Transaction transaction) {
        Transaction sendTransfer = service.saveTransaction(transaction);
        return sendTransfer.getOperationId();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/confirmOperation")
    public Operation confirm(@RequestBody ValidTransaction confirmOperation) {
        return service.validTransaction(confirmOperation);
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

