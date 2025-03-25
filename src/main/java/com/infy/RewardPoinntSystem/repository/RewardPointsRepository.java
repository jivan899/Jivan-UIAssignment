package com.infy.RewardPoinntSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.RewardPoinntSystem.entity.Customer;
import com.infy.RewardPoinntSystem.entity.RewardPoints;

@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
	List<RewardPoints> findByCustomerId(Long customerId);

	RewardPoints findByCustomerAndMonthAndYear(Customer customer, int monthValue, int year);
	
    @Query("SELECT r FROM RewardPoints r WHERE r.customer.id = :customerId " +
            "AND (r.year > :year OR (r.year = :year AND r.month >= :month)) " +
            "ORDER BY r.year DESC, r.month DESC")
     List<RewardPoints> findLastThreeMonthsRewards(
             @Param("customerId") Long customerId,
             @Param("year") int year,
             @Param("month") int month);
}