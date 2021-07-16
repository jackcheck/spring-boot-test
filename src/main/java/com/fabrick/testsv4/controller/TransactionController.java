package com.fabrick.testsv4.controller;

import com.fabrick.testsv4.model.Transactions;
import com.fabrick.testsv4.service.TransactionService;
import com.fabrick.testsv4.utils.Const;
import com.fabrick.testsv4.utils.Utils;
import com.fabrick.testsv4.webClient.GreetingWebClient;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping(value = Const.TRANS_LIST_API)
    public Mono<Transactions> fetchApiTransactionsList(){
        GreetingWebClient gwc = new GreetingWebClient();

        System.out.println("Log fetchApiTransactionsList accountId: " + gwc.getTransactionList());

        //store transactions
        Transactions transactions = gwc.getTransactionList().block();
        if(transactions!=null && transactions.getPayload()!=null && transactions.getPayload().getList()!=null && transactions.getPayload().getList().size()>0 ) {
            for(int i= 0; i<transactions.getPayload().getList().size(); i++){
                transactionService.addNewTransaction(transactions.getPayload().getList().get(i));
            }
        }

        return gwc.getTransactionList();
    }

    @PostMapping(
            value = Const.ACC_BAL_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String fetchApiAccountBalance(
            @RequestParam(required = false) String accountId) {

        GreetingWebClient gwc = new GreetingWebClient();
        System.out.println("Log fetchApiAccountBalance accountId: " + accountId);
        return gwc.getAccountBalance(accountId);
    }

//    @PostMapping(
//            value = Const.ACC_BAL_API,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
//    public Mono<Balance>  fetchApiAccountBalance(
//            @RequestParam(required = false) String accountId) {
//
//        GreetingWebClient gwc = new GreetingWebClient();
//        System.out.println(accountId);
//        return gwc.getAccountBalance(accountId);
//    }

    @PostMapping(
            value = Const.MON_TRANS_API,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String fetchApiMoneyTransfer(
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String receiverName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String amount,
            @RequestParam(required = false) String executionDate) throws ParseException {

        GreetingWebClient gwc = new GreetingWebClient();
        System.out.println("Log fetchApiMoneyTransfer fields: " + accountId + " - " + receiverName + " - " + description + " - " + currency + " - " + amount + " - " + executionDate );

        Utils utils = new Utils();
        return gwc.executeMoneyTransfer(utils.fillMoneyTransfer(accountId, receiverName, description, currency, amount, executionDate), accountId);
    }

}
