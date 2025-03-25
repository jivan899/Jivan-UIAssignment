package com.infy.RewardPoinntSystem.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.RewardPoinntSystem.Exceptions.ResourceNotFoundException;
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
	public List<CustomerTransactionModel> getTransactionsByCustomerId(Long customerId) {
		customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

		List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customerId);

		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException("Transactions", "customerId", customerId);
		}

		return transactions.stream().map(transaction -> modelMapper.map(transaction, CustomerTransactionModel.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CustomerTransactionModel addTransaction(CustomerTransactionModel customerTransactionModel) {
		Customer customer = customerRepository.findById(customerTransactionModel.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id", customerTransactionModel.getCustomerId()));

		CustomerTransaction customerTransaction = CustomerTransaction.builder()
				.amount(customerTransactionModel.getAmount()).date(customerTransactionModel.getDate())
				.spentDetails(customerTransactionModel.getSpentDetails()).customer(customer).build();

		CustomerTransaction savedTransaction = transactionRepository.save(customerTransaction);
		rewardService.CalculateAndSave(savedTransaction);

		return modelMapper.map(savedTransaction, CustomerTransactionModel.class);
	}

	@Override
	public CustomerTransactionModel updateTransaction(Long transactionId, CustomerTransactionModel transactionModel) {
		CustomerTransaction existingTransaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId));

		CustomerTransaction updatedTransaction = CustomerTransaction.builder().id(existingTransaction.getId())
				.spentDetails(transactionModel.getSpentDetails()).amount(transactionModel.getAmount())
				.date(transactionModel.getDate()).customer(existingTransaction.getCustomer()).build();

		CustomerTransaction savedTransaction = transactionRepository.save(updatedTransaction);
		return modelMapper.map(savedTransaction, CustomerTransactionModel.class);
	}

	@Override
	public String deleteTransaction(Long transactionId) {
		if (!transactionRepository.existsById(transactionId)) {
			throw new ResourceNotFoundException("Transaction", "id", transactionId);
		}

		transactionRepository.deleteById(transactionId);
		return "Transaction with ID " + transactionId + " deleted successfully.";
	}

}
