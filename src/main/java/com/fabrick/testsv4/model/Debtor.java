package com.fabrick.testsv4.model;

import lombok.Data;

@Data
public class Debtor{
    public String name;
    public Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
