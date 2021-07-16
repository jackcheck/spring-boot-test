package com.fabrick.testsv4.repository;

import com.fabrick.testsv4.model.List;
import com.fabrick.testsv4.model.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository
        extends MongoRepository<List, String> {

}
