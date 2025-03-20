	package com.infy.RewardPoinntSystem.serviceImpl;
	

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.RewardPoinntSystem.config.ModelMapperConfig;
import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.model.CustomerModel;
import com.infy.RewardPoinntSystem.repository.CustomerRepository;
import com.infy.RewardPoinntSystem.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    ModelMapper modelMapperConfig;

    @Override
    public CustomerModel registerCustomer(CustomerModel customerModel) {
    	
    	Customer customer = modelMapperConfig.map(customerModel, Customer.class);
    	
         Customer customer2 = customerRepository.save(customer);
         CustomerModel model = modelMapperConfig.map(customer2,CustomerModel.class);
         return model;
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }


}
