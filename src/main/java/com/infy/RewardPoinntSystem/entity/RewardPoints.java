package com.infy.RewardPoinntSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardPoints {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private int points;
	private int month;
	private int year;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
