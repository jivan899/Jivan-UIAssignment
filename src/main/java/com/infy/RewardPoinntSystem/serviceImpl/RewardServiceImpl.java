package com.infy.RewardPoinntSystem.serviceImpl;

import java.util.List;

//import java.time.YearMonth;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
//import com.infy.RewardPoinntSystem.entity.RewardPoints;
//import com.infy.RewardPoinntSystem.repository.CustomerTransactionRepository;
//import com.infy.RewardPoinntSystem.repository.RewardPointsRepository;
//
//@Service
//public class RewardService {
//
//    @Autowired
//    private CustomerTransactionRepository transactionRepository;
//
//    @Autowired
//    private RewardPointsRepository rewardPointsRepository;
//
//    public void calculateRewardPoints(Long customerId) {
//        List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customerId);
//        int totalPoints = 0;
//
//        for (CustomerTransaction transaction : transactions) {
//            double amount = transaction.getAmount();
//            if (amount > 100) {
//                totalPoints += (int) ((amount - 100) * 2) + 50;
//            } else if (amount > 50) {
//                totalPoints += (int) (amount - 50);
//            }
//        }
//
//        RewardPoints rewardPoints = new RewardPoints();
//        rewardPoints.setCustomer(transaction.getCustomer());
//        rewardPoints.setPoints(totalPoints);
//        rewardPoints.setMonthYear(YearMonth.now());
//
//        rewardPointsRepository.save(rewardPoints);
//    }
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.RewardPoinntSystem.entity.CustomerTransaction;
import com.infy.RewardPoinntSystem.entity.RewardPoints;
import com.infy.RewardPoinntSystem.repository.CustomerTransactionRepository;
import com.infy.RewardPoinntSystem.repository.RewardPointsRepository;
import com.infy.RewardPoinntSystem.service.RewardService;

import jakarta.transaction.Transactional;

@Service
public class RewardServiceImpl implements RewardService{
    @Autowired
    private RewardPointsRepository rewardPointsRepository;
    
    @Autowired
    private CustomerTransactionRepository transactionRepository;

    @Override
    public List<RewardPoints> getRewardPointsByCustomerId(Long customerId) {
        return rewardPointsRepository.findByCustomerId(customerId);
    }
    
    @Override
    public void CalculateAndSave(CustomerTransaction transaction) {
        CustomerTransaction freshTransaction = transactionRepository.findById(transaction.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        int points = calculatePoints(freshTransaction.getAmount());

        RewardPoints rewardPoints = rewardPointsRepository.findByCustomerAndMonthAndYear(
                freshTransaction.getCustomer(),
                freshTransaction.getDate().getMonthValue(),
                freshTransaction.getDate().getYear()
        );

        if (rewardPoints != null) {
            rewardPoints.setPoints(rewardPoints.getPoints() + points);
        } else {
            rewardPoints = new RewardPoints();
            rewardPoints.setCustomer(freshTransaction.getCustomer());
            rewardPoints.setMonth(freshTransaction.getDate().getMonthValue());
            rewardPoints.setYear(freshTransaction.getDate().getYear());
            rewardPoints.setPoints(points);
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
}