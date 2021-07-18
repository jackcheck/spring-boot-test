package com.fabrick.testsv4.model;

import lombok.Data;

@Data
public class Payload{

    public java.util.List<List> list;
    public MoneyTransferRes moneyTransferRes;

    public java.util.List<List> getList() {

        return list;
    }

    public void setList(java.util.List<List> list) {

        this.list = list;
    }

    public MoneyTransferRes getMoneyTransferRes() {
        return moneyTransferRes;
    }

    public void setMoneyTransferRes(MoneyTransferRes moneyTransferRes) {
        this.moneyTransferRes = moneyTransferRes;
    }
}
