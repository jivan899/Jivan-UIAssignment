package com.infy.RewardPoinntSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.model.CustomerModel;
import com.infy.RewardPoinntSystem.serviceImpl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerService;

	@PostMapping("/register")
	public CustomerModel registerCustomer(@RequestBody CustomerModel customer) {
		return customerService.registerCustomer(customer);
	}

	@PostMapping("/login")
	public Customer login(@RequestParam String email, @RequestParam String password) {
		return customerService.login(email, password);
	}
}
