package com.example.courseprojectfinal.service;


import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.Transaction;
import com.example.courseprojectfinal.repository.TransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransService {
    @Autowired
    TransRepository repository;

    public Transaction saveTransaction(Transaction transaction) {
        return repository.saveTransaction(transaction);
    }

    public Operation validTransaction (Operation op){
        return repository.validTransaction(op);
    }

}
