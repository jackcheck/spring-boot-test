package com.fabrick.testsv4.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class NaturalPersonBeneficiary{
    public String fiscalCode1;
    public String fiscalCode2;
    public String fiscalCode3;
    public String fiscalCode4;
    public String fiscalCode5;

    public String getFiscalCode1() {
        return fiscalCode1;
    }

    public void setFiscalCode1(String fiscalCode1) {
        this.fiscalCode1 = fiscalCode1;
    }

    public String getFiscalCode2() {
        return fiscalCode2;
    }

    public void setFiscalCode2(String fiscalCode2) {
        this.fiscalCode2 = fiscalCode2;
    }

    public String getFiscalCode3() {
        return fiscalCode3;
    }

    public void setFiscalCode3(String fiscalCode3) {
        this.fiscalCode3 = fiscalCode3;
    }

    public String getFiscalCode4() {
        return fiscalCode4;
    }

    public void setFiscalCode4(String fiscalCode4) {
        this.fiscalCode4 = fiscalCode4;
    }

    public String getFiscalCode5() {
        return fiscalCode5;
    }

    public void setFiscalCode5(String fiscalCode5) {
        this.fiscalCode5 = fiscalCode5;
    }
}
