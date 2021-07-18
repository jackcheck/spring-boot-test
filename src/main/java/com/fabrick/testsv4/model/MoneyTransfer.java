package com.fabrick.testsv4.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MoneyTransfer {

    public Creditor creditor;
    public String executionDate;
    public String uri;
    public String description;
    public int amount;
    public String currency;
    public boolean isUrgent;
    public boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public TaxRelief taxRelief;
    public String status;

    public Creditor getCreditor() {

        return creditor;
    }

    public void setCreditor(Creditor creditor) {

        this.creditor = creditor;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    public boolean isInstant() {
        return isInstant;
    }

    public void setInstant(boolean instant) {
        isInstant = instant;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeAccountId() {
        return feeAccountId;
    }

    public void setFeeAccountId(String feeAccountId) {
        this.feeAccountId = feeAccountId;
    }

    public TaxRelief getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(TaxRelief taxRelief) {
        this.taxRelief = taxRelief;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}









