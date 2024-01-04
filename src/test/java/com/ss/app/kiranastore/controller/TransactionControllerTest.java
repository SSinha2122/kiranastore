package com.ss.app.kiranastore.controller;

import com.ss.app.kiranastore.model.Transaction;
import com.ss.app.kiranastore.model.TransactionType;
import com.ss.app.kiranastore.manager.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recordTransaction_shouldReturnSavedTransaction() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.00");
        String currency = "USD";
        TransactionType type = TransactionType.CREDIT;
        Transaction expectedTransaction = new Transaction();

        when(transactionService.recordTransaction(amount, currency, type)).thenReturn(expectedTransaction);

        // Act
        Transaction savedTransaction = transactionController.recordTransaction(amount, currency, type);

        // Assert
        assertEquals(expectedTransaction, savedTransaction);
        verify(transactionService, times(1)).recordTransaction(amount, currency, type);
    }

    @Test
    void getDailyTransactions_shouldReturnListOfTransactions() {
        // Arrange
        List<Transaction> expectedTransactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionService.getDailyTransactions()).thenReturn(expectedTransactions);

        // Act
        List<Transaction> dailyTransactions = transactionController.getDailyTransactions();

        // Assert
        assertEquals(expectedTransactions, dailyTransactions);
        verify(transactionService, times(1)).getDailyTransactions();
    }
}
