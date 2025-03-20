package com.infy.RewardPoinntSystem.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransactionModel {

	
    private String spentDetails;
    private double amount;
    private LocalDate date;
    private Long customerId;
}
