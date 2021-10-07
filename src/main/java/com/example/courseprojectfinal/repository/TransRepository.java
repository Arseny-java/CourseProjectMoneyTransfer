package com.example.courseprojectfinal.repository;


import com.example.courseprojectfinal.model.Operation;
import com.example.courseprojectfinal.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

@Component
public class TransRepository {

    private final List<Transaction> transRepository;

    public TransRepository() {
        this.transRepository = new Vector<>();
    }

    public Transaction saveTransaction (Transaction transaction){
    transRepository.add(transaction);
    return transaction;
}

    public Operation validTransaction (Operation op){
       return op;
    }

    public int size (){
       return transRepository.size();
    }
}
