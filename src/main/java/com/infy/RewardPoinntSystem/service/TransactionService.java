package com.infy.RewardPoinntSystem.service;


import java.util.List;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.model.CustomerTransactionModel;

public interface TransactionService {
    
	CustomerTransactionModel addTransaction(CustomerTransactionModel customerTransaction) throws RuntimeException;

    List<CustomerTransactionModel> getTransactionsByCustomerId(Long customerId);

    CustomerTransactionModel updateTransaction(Long transactionId, CustomerTransactionModel transactionModel);

    String deleteTransaction(Long transactionId);
}
