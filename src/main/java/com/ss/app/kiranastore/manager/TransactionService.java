package com.ss.app.kiranastore.manager;

import com.ss.app.kiranastore.model.Transaction;
import com.ss.app.kiranastore.model.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction recordTransaction(BigDecimal amount, String currency, TransactionType type);

    List<Transaction> getDailyTransactions();
}
