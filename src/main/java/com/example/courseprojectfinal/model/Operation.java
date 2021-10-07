package com.example.courseprojectfinal.model;

import java.util.Random;

public class Operation {

    private final String operationId;


    public Operation(String operationId) {
        this.operationId = operationId;
    }

    public static String generationCode() {
        Random rand = new Random();
        int maxValueA = 2147483647;
        int a = rand.nextInt(maxValueA);
        return String.valueOf(a);
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return operationId;
    }
}
