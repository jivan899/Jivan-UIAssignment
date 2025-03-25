package com.infy.RewardPoinntSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String spentDetails;
	private double amount;
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}