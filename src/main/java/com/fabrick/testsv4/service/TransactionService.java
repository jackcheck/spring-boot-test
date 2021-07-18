package com.fabrick.testsv4.service;

import com.fabrick.testsv4.model.MoneyTransfer;
import com.fabrick.testsv4.repository.MoneyTransferRepository;
import com.fabrick.testsv4.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MoneyTransferRepository moneyTransferRepository;
    private final MongoTemplate mongoTemplate;


    public List<com.fabrick.testsv4.model.List> getAllTransactions(){

        return transactionRepository.findAll();
    }

    public void addNewTransaction(com.fabrick.testsv4.model.List transaction) {

        if (!isTransactionAlreadyStored(transaction.getAccountId(), transaction.getActivatedDate())) {
            try {
                transactionRepository.save(transaction);
                log.info("transaction stored" + transaction);
            } catch (Exception ex) {
                log.error("Exception during transaction storing" + ex);

            }
        }

    }

    public void addNewMoneyTransfer(MoneyTransfer moneyTransfer) {

        try {
            moneyTransferRepository.save(moneyTransfer);
            log.info("moneyTransfer stored" + moneyTransfer);
        } catch (Exception ex) {
            log.error("Exception during moneyTransfer storing" + ex);
        }

    }

    private boolean isTransactionAlreadyStored(String accountId, String activatedDate) {

        boolean isTransactionAlreadyStored = true;

        Query query = new Query();
        query.addCriteria(Criteria.where("accountId").is(accountId));
        query.addCriteria(Criteria.where("activatedDate").is(activatedDate));

        try {
            List<com.fabrick.testsv4.model.List> transactions = mongoTemplate.find(query, com.fabrick.testsv4.model.List.class);

            if (transactions.isEmpty()){
                isTransactionAlreadyStored = false;
            } else {
                log.info("found many transactions with accountId: " + accountId + " and activatedDate: " + activatedDate);
            }
        } catch (Exception ex) {
            log.error("Exception find all transaction on MongoDb" + ex);

        }
        return isTransactionAlreadyStored;
    }

    public List<MoneyTransfer> showAllMoneyTransferByStatus(String status){

        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status));
        List<MoneyTransfer> moneyTransfer = new ArrayList<MoneyTransfer>();
        try {
           moneyTransfer = mongoTemplate.find(query, com.fabrick.testsv4.model.MoneyTransfer.class);
        } catch (Exception ex) {
            log.error("Exception find all transaction on MongoDb" + ex);
        }
        return moneyTransfer;

    }

}
