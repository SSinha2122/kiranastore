package com.ss.app.kiranastore.controller;

import com.ss.app.kiranastore.model.Transaction;
import com.ss.app.kiranastore.model.TransactionType;
import com.ss.app.kiranastore.manager.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/recordTransaction")
    public Transaction recordTransaction(
            @RequestParam BigDecimal amount,
            @RequestParam String currency,
            @RequestParam TransactionType type) {
        log.info("Request received to record transaction-->{}{}{}",amount,currency,type);
        return transactionService.recordTransaction(amount, currency, type);
    }

    @GetMapping("/daily")
    public List<Transaction> getDailyTransactions() {
        return transactionService.getDailyTransactions();
    }
}
