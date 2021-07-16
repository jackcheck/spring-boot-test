package com.fabrick.testsv4.utils;

import com.fabrick.testsv4.model.MoneyTransfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public MoneyTransfer fillMoneyTransfer(String accountId, String receiverName, String description, String currency, String amount, String executionDate) throws ParseException {

        MoneyTransfer moneyTransfer = new MoneyTransfer();
        //creditor
        MoneyTransfer.Creditor creditor = new MoneyTransfer.Creditor();
        creditor.setName(receiverName);
        //account
        MoneyTransfer.Creditor.Account account = new MoneyTransfer.Creditor.Account();
        account.setAccountCode("IT23A0336844430152923804660");
        account.setBicCode("SELBIT2BXXX");
        creditor.setAccount(account);
        //address
        MoneyTransfer.Creditor.Address address = new MoneyTransfer.Creditor.Address();
        address.setAddress("");
        address.setCity("");
        address.setCountryCode("");
        creditor.setAddress(address);
        moneyTransfer.setCreditor(creditor);
        //moneytransfer
//        moneyTransfer.setExecutionDate(convertStringToDate(executionDate));
        moneyTransfer.setExecutionDate(executionDate);
        moneyTransfer.setUri("REMITTANCE_INFORMATION");
        moneyTransfer.setDescription(description);
        moneyTransfer.setAmount(Integer.parseInt(amount));
        moneyTransfer.setCurrency(currency);
        moneyTransfer.setUrgent(false);
        moneyTransfer.setInstant(false);
        moneyTransfer.setFeeType("SHA");
        moneyTransfer.setFeeAccountId(accountId);
        //taxRelief
        MoneyTransfer.TaxRelief taxRelief = new MoneyTransfer.TaxRelief();
        taxRelief.setTaxReliefId("L449");
        taxRelief.setCondoUpgrade(false);
        taxRelief.setCreditorFiscalCode("56258745832");
        taxRelief.setBeneficiaryType("NATURAL_PERSON");
        //naturalPersonBeneficiary
        MoneyTransfer.TaxRelief.NaturalPersonBeneficiary naturalPersonBeneficiary = new MoneyTransfer.TaxRelief.NaturalPersonBeneficiary();
        naturalPersonBeneficiary.setFiscalCode1("MRLFNC81L04A859L");
        taxRelief.setNaturalPersonBeneficiary(naturalPersonBeneficiary);
        //legalPersonBeneficiary
        MoneyTransfer.TaxRelief.LegalPersonBeneficiary legalPersonBeneficiary = new MoneyTransfer.TaxRelief.LegalPersonBeneficiary();
        legalPersonBeneficiary.setFiscalCode("56258745832");
        legalPersonBeneficiary.setLegalRepresentativeFiscalCode("");
        taxRelief.setLegalPersonBeneficiary(legalPersonBeneficiary);
        moneyTransfer.setTaxRelief(taxRelief);

        return moneyTransfer;

    }

    public Date convertStringToDate (String stringDate) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        return date;
    }
}
