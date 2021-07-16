package com.fabrick.testsv4.model;

import lombok.Data;

@Data
public class Payload{

    public java.util.List<List> list;

    public java.util.List<List> getList() {

        return list;
    }

    public void setList(java.util.List<List> list) {

        this.list = list;
    }
}
