package com.infy.RewardPoinntSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.model.CustomerTransactionModel;
import com.infy.RewardPoinntSystem.serviceImpl.TransactionServiceImpl;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.infy.RewardPoinntSystem.model.CustomerTransactionModel;
import com.infy.RewardPoinntSystem.serviceImpl.TransactionServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionServiceImpl transactionService;

	@PostMapping("/add")
	public CustomerTransactionModel addTransaction(@RequestBody CustomerTransactionModel transaction) {
		return transactionService.addTransaction(transaction);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<CustomerTransactionModel>> getTransactionsByCustomerId(@PathVariable Long customerId) {
		List<CustomerTransactionModel> transactions = transactionService.getTransactionsByCustomerId(customerId);
		return ResponseEntity.ok(transactions);
	}

	@PutMapping("/update/{transactionId}")
	public CustomerTransactionModel updateTransaction(@PathVariable Long transactionId,
			@RequestBody CustomerTransactionModel transactionModel) {
		return transactionService.updateTransaction(transactionId, transactionModel);
	}

	@DeleteMapping("/delete/{transactionId}")
	public String deleteTransaction(@PathVariable Long transactionId) {
		return transactionService.deleteTransaction(transactionId);
	}
}
