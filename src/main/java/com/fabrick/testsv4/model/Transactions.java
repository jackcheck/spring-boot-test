package com.fabrick.testsv4.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class Transactions {

    private static final long serialVersionUID = 1L;

    public String status;
    public List<String> error;
    public Payload payload;

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public List<String> getError() {

        return error;
    }

    public void setError(List<String> error) {

        this.error = error;
    }

    public Payload getPayload() {

        return payload;
    }

    public void setPayload(Payload payload) {

        this.payload = payload;
    }

}
