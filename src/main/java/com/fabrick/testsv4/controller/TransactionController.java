package com.fabrick.testsv4.controller;

import com.fabrick.testsv4.model.List;
import com.fabrick.testsv4.model.MoneyTransfer;
import com.fabrick.testsv4.model.Response;
import com.fabrick.testsv4.service.TransactionService;
import com.fabrick.testsv4.utils.Const;
import com.fabrick.testsv4.utils.Utils;
import com.fabrick.testsv4.webClient.GreetingWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    //Get transactions list from API
    //input parameters: accountId, fromAccountingDate, toAccountingDate
    @PostMapping(
            value = Const.TRANS_LIST_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Response fetchApiTransactionsList(
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String fromAccountingDate,
            @RequestParam(required = false) String toAccountingDate) throws IOException {
        GreetingWebClient gwc = new GreetingWebClient();
        Response response = new Response();
        if (Const.isMock) {
            //SUCCESS transactionList.json
            //FAIL transactionList_KO.json
            response = Utils.getMockResponse("transactionList.json");
            log.info("Log fetchApiTransactionsList : is mocked response");

        } else {
            response = gwc.getTransactionList(accountId, fromAccountingDate, toAccountingDate);
        }

        log.info("Log fetchApiTransactionsList accountId: " + accountId);

        //store transactions list
        if (response != null && response.getPayload() != null && response.getPayload().getList() != null && response.getPayload().getList().size() > 0) {
            for (int i = 0; i < response.getPayload().getList().size(); i++) {
                transactionService.addNewTransaction(response.getPayload().getList().get(i));
            }
        }

        log.info("transactions: " + response);
        return response;
    }

    //Get transactions list from DB
    //input parameter: NONE
    @GetMapping(path = Const.TRANS_LIST_API)
    public java.util.List<List> fetchDbTransactionsList() {
        return transactionService.getAllTransactions();
    }


    //Get Account Balance
    //input parameters: accountId
    @PostMapping(
            value = Const.ACC_BAL_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Response fetchApiAccountBalance(
            @RequestParam(required = false) String accountId) throws IOException {

        Response response = new Response();

        if (Const.isMock) {
            //SUCCESS accountBalance.json
            //FAIL accountBalance_KO.json
            response = Utils.getMockResponse("accountBalance_KO.json");
            log.info("Log fetchApiAccountBalance : is mocked response");
        } else {
            GreetingWebClient gwc = new GreetingWebClient();
            response = gwc.getAccountBalance(accountId);
        }

        log.info("Log fetchApiAccountBalance accountId: " + accountId);

        return response;
    }

    //Money Transfer
    //input parameters: accountId, receiverName, description, currency, amount, executionDate
    @PostMapping(
            value = Const.MON_TRANS_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Response fetchApiMoneyTransfer(
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String receiverName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String amount,
            @RequestParam(required = false) String executionDate) throws ParseException, IOException {

        log.info("Log fetchApiMoneyTransfer fields: " + accountId + " - " + receiverName + " - " + description + " - " + currency + " - " + amount + " - " + executionDate);

        MoneyTransfer moneyTransfer = Utils.fillMoneyTransfer(accountId, receiverName, description, currency, amount, executionDate);
        //transactionService.addNewMoneyTransfer(moneyTransfer);

        Response response = new Response();
        if (Const.isMock) {
            //SUCCESS moneyTransfer.json
            //FAIL moneyTransfer_KO.json
            response = Utils.getMockResponse("moneyTransfer_KO.json");
            log.info("Log fetchApiMoneyTransfer : is mocked response");
        } else {
            GreetingWebClient gwc = new GreetingWebClient();
            response = gwc.executeMoneyTransfer(moneyTransfer, accountId);
        }

        if(response!=null) {
            moneyTransfer.setStatus(response.getStatus());
            transactionService.addNewMoneyTransfer(moneyTransfer);
        }

        return response;
    }

    //Money Transfer stored into the database
    //input parameter: status = OK / KO
    @PostMapping(
            value = Const.MON_TRANSLIST_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public java.util.List<MoneyTransfer> showAllMoneyTransferByStatus(
            @RequestParam(required = true) String status
            ) {
        return transactionService.showAllMoneyTransferByStatus(status);
    }


}
