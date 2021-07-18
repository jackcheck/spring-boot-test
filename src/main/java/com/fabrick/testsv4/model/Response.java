package com.fabrick.testsv4.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private static final long serialVersionUID = 1L;

    public String status;
    public List<Error> errors;
    public Payload payload;

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public List<Error> getErrors() {

        return errors;
    }

    public void setErrors(List<Error> errors) {

        this.errors = errors;
    }

    public Payload getPayload() {

        return payload;
    }

    public void setPayload(Payload payload) {

        this.payload = payload;
    }

}
