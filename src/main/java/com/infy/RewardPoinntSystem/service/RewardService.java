package com.infy.RewardPoinntSystem.service;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.entity.RewardPoints;
import java.util.List;

public interface RewardService {
	List<RewardPoints> getRewardPointsByCustomerId(Long customerId);

	void CalculateAndSave(CustomerTransaction transaction);

	int calculatePoints(double amount);
}
