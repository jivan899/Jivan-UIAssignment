package com.infy.RewardPoinntSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.RewardPoinntSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email); 
}
