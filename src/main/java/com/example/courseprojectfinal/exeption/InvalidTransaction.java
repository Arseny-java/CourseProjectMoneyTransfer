package com.example.courseprojectfinal.exeption;

public class InvalidTransaction extends RuntimeException {
    public InvalidTransaction(String msg) {
        super(msg);
    }
}
