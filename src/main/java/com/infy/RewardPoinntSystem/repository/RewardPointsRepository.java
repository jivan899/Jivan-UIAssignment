package com.infy.RewardPoinntSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.entity.RewardPoints;

import java.util.List;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    List<RewardPoints> findByCustomerId(Long customerId);

	RewardPoints findByCustomerAndMonthAndYear(Customer customer, int monthValue, int year);
}