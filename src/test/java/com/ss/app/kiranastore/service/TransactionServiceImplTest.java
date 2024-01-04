package com.ss.app.kiranastore.service;

import com.ss.app.kiranastore.model.Transaction;
import com.ss.app.kiranastore.model.TransactionType;
import com.ss.app.kiranastore.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recordTransaction_shouldSaveTransaction() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.00");
        String currency = "USD";
        TransactionType type = TransactionType.CREDIT;
        Transaction expectedTransaction = new Transaction();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);

        // Act
        Transaction savedTransaction = transactionService.recordTransaction(amount, currency, type);

        // Assert
        assertEquals(expectedTransaction, savedTransaction);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void getDailyTransactions_shouldReturnListOfTransactions() {
        // Arrange
        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        when(transactionRepository.findByTimestampBetween(start, end)).thenReturn(Arrays.asList(
                new Transaction(),
                new Transaction()
        ));

        // Act
        List<Transaction> dailyTransactions = transactionService.getDailyTransactions();

        // Assert
        assertEquals(2, dailyTransactions.size());
        verify(transactionRepository, times(1)).findByTimestampBetween(start, end);
    }
}
