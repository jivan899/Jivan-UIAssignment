package com.infy.RewardPoinntSystem.serviceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.RewardPoinntSystem.Exceptions.ResourceNotFoundException;
import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.entity.RewardPoints;
import com.infy.RewardPoinntSystem.model.ThreeMonthRewardsModel;
import com.infy.RewardPoinntSystem.repository.CustomerTransactionRepository;
import com.infy.RewardPoinntSystem.repository.RewardPointsRepository;
import com.infy.RewardPoinntSystem.service.RewardService;

import jakarta.transaction.Transactional;

@Service
public class RewardServiceImpl implements RewardService {
	@Autowired
	private RewardPointsRepository rewardPointsRepository;

	@Autowired
	private CustomerTransactionRepository transactionRepository;

	@Override
	public List<RewardPoints> getRewardPointsByCustomerId(Long customerId) {
		return rewardPointsRepository.findByCustomerId(customerId);
	}

	@Override
	@Transactional
	public void CalculateAndSave(CustomerTransaction transaction) {
		CustomerTransaction freshTransaction = transactionRepository.findById(transaction.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transaction.getId()));

		int points = calculatePoints(freshTransaction.getAmount());

		RewardPoints rewardPoints = rewardPointsRepository.findByCustomerAndMonthAndYear(freshTransaction.getCustomer(),
				freshTransaction.getDate().getMonthValue(), freshTransaction.getDate().getYear());

		if (rewardPoints != null) {
			rewardPoints.setPoints(rewardPoints.getPoints() + points);
		} else {
			rewardPoints = RewardPoints.builder().customer(freshTransaction.getCustomer())
					.month(freshTransaction.getDate().getMonthValue()).year(freshTransaction.getDate().getYear())
					.points(points).build();
		}

		rewardPointsRepository.save(rewardPoints);
	}

	@Override
	public int calculatePoints(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2;
		}
		if (amount > 50) {
			points += (Math.min(amount, 100) - 50);
		}
		return points;
	}

	public List<RewardPoints> getAllRewardPoints() {
		List<RewardPoints> allRecords = rewardPointsRepository.findAll();
		return allRecords;
	}

	@Override
	public ThreeMonthRewardsModel getLastThreeMonthsRewards(Long customerId) {
		YearMonth threeMonthsAgo = YearMonth.now().minusMonths(3);

		List<RewardPoints> rewards = rewardPointsRepository.findLastThreeMonthsRewards(customerId,
				threeMonthsAgo.getYear(), threeMonthsAgo.getMonthValue());

		Map<YearMonth, Integer> monthlyPoints = new HashMap<>();

		for (RewardPoints reward : rewards) {
			YearMonth monthKey = YearMonth.of(reward.getYear(), reward.getMonth());
			int currentPoints = monthlyPoints.getOrDefault(monthKey, 0);
			monthlyPoints.put(monthKey, currentPoints + reward.getPoints());
		}

		YearMonth currentMonth = YearMonth.now();
		YearMonth month1 = currentMonth.minusMonths(2);
		YearMonth month2 = currentMonth.minusMonths(1);
		YearMonth month3 = currentMonth;

		int month1Points = monthlyPoints.getOrDefault(month1, 0);
		int month2Points = monthlyPoints.getOrDefault(month2, 0);
		int month3Points = monthlyPoints.getOrDefault(month3, 0);
		int total = month1Points + month2Points + month3Points;

		if (total == 0) {
			throw new ResourceNotFoundException("Reward points", "customerId", customerId);
		}

		return new ThreeMonthRewardsModel(
				String.format("%s %d - %d", month1.getMonth(), month1.getYear(), month1Points),
				String.format("%s %d - %d", month2.getMonth(), month2.getYear(), month2Points),
				String.format("%s %d - %d", month3.getMonth(), month3.getYear(), month3Points), total);
	}
}