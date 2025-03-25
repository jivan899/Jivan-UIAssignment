package com.infy.RewardPoinntSystem.service;

import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.model.CustomerModel;

public interface CustomerService {
	Customer login(String email, String password);

	CustomerModel registerCustomer(CustomerModel customerModel);
}
