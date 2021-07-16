package com.fabrick.testsv4.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class List{
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
}
