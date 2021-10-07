package com.example.courseprojectfinal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ValidTransaction {

    private final Operation operationId;
    private final String code;

    @JsonCreator
    public ValidTransaction(@JsonProperty("operationId") Operation operationId,
                            @JsonProperty("code") String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public Operation getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }
}
