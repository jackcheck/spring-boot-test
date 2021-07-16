package com.fabrick.testsv4.model;

import lombok.Data;

import java.util.List;

@Data
public class Balance {

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

    public class Payload{
        public String accountId;
        public String iban;
        public String abiCode;
        public String cabCode;
        public String countryCode;
        public String internationalCin;
        public String nationalCin;
        public String account;
        public String alias;
        public String productName;
        public String holderName;
        public String activatedDate;
        public String currency;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getAbiCode() {
            return abiCode;
        }

        public void setAbiCode(String abiCode) {
            this.abiCode = abiCode;
        }

        public String getCabCode() {
            return cabCode;
        }

        public void setCabCode(String cabCode) {
            this.cabCode = cabCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getInternationalCin() {
            return internationalCin;
        }

        public void setInternationalCin(String internationalCin) {
            this.internationalCin = internationalCin;
        }

        public String getNationalCin() {
            return nationalCin;
        }

        public void setNationalCin(String nationalCin) {
            this.nationalCin = nationalCin;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getHolderName() {
            return holderName;
        }

        public void setHolderName(String holderName) {
            this.holderName = holderName;
        }

        public String getActivatedDate() {
            return activatedDate;
        }

        public void setActivatedDate(String activatedDate) {
            this.activatedDate = activatedDate;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}
