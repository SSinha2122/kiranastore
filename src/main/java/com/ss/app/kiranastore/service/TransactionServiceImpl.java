package com.ss.app.kiranastore.service;

import com.ss.app.kiranastore.manager.TransactionService;
import com.ss.app.kiranastore.model.Transaction;
import com.ss.app.kiranastore.model.TransactionType;
import com.ss.app.kiranastore.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction recordTransaction(BigDecimal amount, String currency, TransactionType type) {
        try {
            Transaction transaction = new Transaction();
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setAmount(type == TransactionType.CREDIT ? amount : amount.negate());
            transaction.setCurrency(currency);
            return transactionRepository.save(transaction);
        }catch (Exception e){
            log.error("Exception inside record transaction : {} {}",e,e.getMessage());
        }
        return null;
    }

    @Override
    public List<Transaction> getDailyTransactions() {
        try {
            LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
            LocalDateTime end = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
            return transactionRepository.findByTimestampBetween(start, end);
        }catch (Exception e){
            log.error("Exception inside getDailyTransactions : {} {}",e,e.getMessage());
        }
        return null;
    }
}
