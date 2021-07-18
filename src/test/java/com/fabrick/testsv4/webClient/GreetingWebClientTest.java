package com.fabrick.testsv4.webClient;

import com.fabrick.testsv4.model.MoneyTransfer;
import com.fabrick.testsv4.model.Response;
import com.fabrick.testsv4.utils.Const;
import com.fabrick.testsv4.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingWebClientTest {

    WebTestClient client = WebTestClient
            .bindToServer()
            .baseUrl(Const.BASE_URL)
            .build();

    GreetingWebClient greetingWebClient = new GreetingWebClient();


    //always responde with 200
    @Test
    void getTransactionList() throws JsonProcessingException {

        String idAccount = "14537780";
        String fromAccountingDate = "2019-01-01";
        String toAccountingDate = "2019-12-01";

        Response response = greetingWebClient.getTransactionList(idAccount, fromAccountingDate, toAccountingDate);
        assertTrue(response.getPayload().getList().size()>0 && response.getStatus().equals("OK"));

    }

    //always responde with 200
    @Test
    void getAccountBalance() throws JsonProcessingException {

        String idAccount = "14537780";

        Response response = greetingWebClient.getAccountBalance(idAccount);
        assertTrue(response.getPayload().getList().size()>0 && response.getStatus().equals("OK"));

    }

    //always responde with 400 Bad Request
    @Test
    void executeMoneyTransfer() throws ParseException, JsonProcessingException {

        String idAccount = "14537780";
        String receiverName = "Receiver Name";
        String description = "description text";
        String currency = "EUR";
        String amount = "100";
        String executionDate = "2020-12-12";
        MoneyTransfer moneyTransfer = Utils.fillMoneyTransfer(idAccount, receiverName, description, currency, amount, executionDate);

        Response response = greetingWebClient.executeMoneyTransfer(moneyTransfer, idAccount);
        assertTrue(response.getStatus().equals("KO"));


    }


}