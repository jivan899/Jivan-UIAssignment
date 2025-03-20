package com.infy.RewardPoinntSystem.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.model.CustomerTransactionModel;
import com.infy.RewardPoinntSystem.repository.CustomerRepository;
import com.infy.RewardPoinntSystem.repository.CustomerTransactionRepository;
import com.infy.RewardPoinntSystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerTransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RewardServiceImpl rewardService;

    @Autowired
    private ModelMapper modelMapper;


    

    
    @Override
    @Transactional
	public CustomerTransactionModel addTransaction(CustomerTransactionModel customerTransactionModel) {
	
//           CustomerTransaction transaction = modelMapper.map(customerTransactionModel, CustomerTransaction.class);
           
    	
    	CustomerTransaction customerTransaction = new CustomerTransaction();
    	
    	customerTransaction.setAmount(customerTransactionModel.getAmount());
    	customerTransaction.setDate(customerTransactionModel.getDate());
    	customerTransaction.setSpentDetails(customerTransactionModel.getSpentDetails());

           Customer customer = customerRepository.findById(customerTransactionModel.getCustomerId())
                   .orElseThrow(() -> new RuntimeException("Customer not found"));
           customerTransaction.setCustomer(customer);

           CustomerTransaction savedTransaction = transactionRepository.save(customerTransaction);
           
           rewardService.CalculateAndSave(savedTransaction);

           return modelMapper.map(savedTransaction, CustomerTransactionModel.class);

	}



    @Override
    public List<CustomerTransactionModel> getTransactionsByCustomerId(Long customerId) {
        List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customerId);

        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, CustomerTransactionModel.class))
                .collect(Collectors.toList());
    }
    

    
    
    @Override
    public CustomerTransactionModel updateTransaction(Long transactionId, CustomerTransactionModel transactionModel) {
    	
        CustomerTransaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        existingTransaction.setSpentDetails(transactionModel.getSpentDetails());
        existingTransaction.setAmount(transactionModel.getAmount());
        existingTransaction.setDate(transactionModel.getDate());

        CustomerTransaction updatedTransaction = transactionRepository.save(existingTransaction);

        return modelMapper.map(updatedTransaction, CustomerTransactionModel.class);
    }


    @Override
    public String deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found");
        }

        transactionRepository.deleteById(transactionId);
        return "Transaction with ID " + transactionId + " deleted successfully.";
    }




}
