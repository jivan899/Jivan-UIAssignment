package com.infy.RewardPoinntSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerId(Long customerId);
}