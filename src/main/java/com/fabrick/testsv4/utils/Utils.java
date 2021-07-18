package com.fabrick.testsv4.utils;

import com.fabrick.testsv4.exception.ClientDataException;
import com.fabrick.testsv4.exception.TransactionServiceException;
import com.fabrick.testsv4.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Utils {

    public static MoneyTransfer fillMoneyTransfer(String accountId, String receiverName, String description, String currency, String amount, String executionDate) throws ParseException {

        MoneyTransfer moneyTransfer = new MoneyTransfer();
        //creditor
        Creditor creditor = new Creditor();
        creditor.setName(receiverName);
        //account
        Account account = new Account();
        account.setAccountCode("IT23A0336844430152923804660");  //default value
        account.setBicCode("SELBIT2BXXX");  //default value
        creditor.setAccount(account);
        //address
        Address address = new Address();
        address.setAddress("");
        address.setCity("");
        address.setCountryCode("");
        creditor.setAddress(address);
        moneyTransfer.setCreditor(creditor);
        //moneytransfer
//        moneyTransfer.setExecutionDate(convertStringToDate(executionDate));
        moneyTransfer.setExecutionDate(executionDate);
        moneyTransfer.setUri("REMITTANCE_INFORMATION");  //default value
        moneyTransfer.setDescription(description);
        moneyTransfer.setAmount(Integer.parseInt(amount));
        moneyTransfer.setCurrency(currency);
        moneyTransfer.setUrgent(false);  //default value
        moneyTransfer.setInstant(false);  //default value
        moneyTransfer.setFeeType("SHA");  //default value
        moneyTransfer.setFeeAccountId(accountId);
        //taxRelief
        TaxRelief taxRelief = new TaxRelief();
        taxRelief.setTaxReliefId("L449");  //default value
        taxRelief.setCondoUpgrade(false);  //default value
        taxRelief.setCreditorFiscalCode("56258745832");  //default value
        taxRelief.setBeneficiaryType("NATURAL_PERSON");  //default value
        //naturalPersonBeneficiary
        NaturalPersonBeneficiary naturalPersonBeneficiary = new NaturalPersonBeneficiary();
        naturalPersonBeneficiary.setFiscalCode1("MRLFNC81L04A859L");  //default value
        taxRelief.setNaturalPersonBeneficiary(naturalPersonBeneficiary);
        //legalPersonBeneficiary
        LegalPersonBeneficiary legalPersonBeneficiary = new LegalPersonBeneficiary();
        legalPersonBeneficiary.setFiscalCode("56258745832");  //default value
        legalPersonBeneficiary.setLegalRepresentativeFiscalCode("");
        taxRelief.setLegalPersonBeneficiary(legalPersonBeneficiary);
        moneyTransfer.setTaxRelief(taxRelief);

        return moneyTransfer;

    }

    public Date convertStringToDate (String stringDate) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        return date;
    }

    public static Mono<RuntimeException> handle4xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            log.error("ErrorResponse Code is " + clientResponse.rawStatusCode() + " and the exception message is : " + message);
            throw new ClientDataException(message);
        });
    }

    public static Mono<TransactionServiceException> handle5xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            log.error("ErrorResponse Code is " + clientResponse.rawStatusCode() + " and the exception message is : " + message);
            throw new TransactionServiceException(message);
        });
    }

    public static Response setResult(WebClientResponseException ex) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Response er = objectMapper.readValue(ex.getResponseBodyAsString(), Response.class);

        return er;
    }

    public static Response getMockResponse(String fileName) throws IOException {

        Response response = new Response();
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File file = new ClassPathResource(fileName).getFile();

        response = mapper.readValue(file, Response.class);

        return response;

    }

}
