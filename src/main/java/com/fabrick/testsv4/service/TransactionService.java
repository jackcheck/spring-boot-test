package com.fabrick.testsv4.service;

import com.fabrick.testsv4.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MongoTemplate mongoTemplate;

    public List<com.fabrick.testsv4.model.List> getAllTransactions(){

        return transactionRepository.findAll();
    }

    public void addNewTransaction(com.fabrick.testsv4.model.List transaction) {

        if (!isTransactionAlreadyStored(transaction.getAccountId(), transaction.getActivatedDate())) {
            transactionRepository.save(transaction);
            System.out.println("transaction stored" + transaction);
        }

    }

    private boolean isTransactionAlreadyStored(String accountId, String activatedDate) {

        boolean isTransactionAlreadyStored = true;

        Query query = new Query();
        query.addCriteria(Criteria.where("accountId").is(accountId));
        query.addCriteria(Criteria.where("activatedDate").is(activatedDate));

        List<com.fabrick.testsv4.model.List> transactions = mongoTemplate.find(query, com.fabrick.testsv4.model.List.class);

        if (transactions.isEmpty()){
            isTransactionAlreadyStored = false;
        } else {
            System.out.println("found many transactions with accountId: " + accountId + " and activatedDate: " + activatedDate);
        }

        return isTransactionAlreadyStored;
    }
}
