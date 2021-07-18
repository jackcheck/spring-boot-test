package com.fabrick.testsv4.repository;

import com.fabrick.testsv4.model.List;
import com.fabrick.testsv4.model.MoneyTransfer;
import com.fabrick.testsv4.model.MoneyTransferRes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoneyTransferRepository
        extends MongoRepository<MoneyTransfer, String> {

}
