package com.infy.RewardPoinntSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
	List<CustomerTransaction> findByCustomerId(Long customerId);
}