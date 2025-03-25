package com.infy.RewardPoinntSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeMonthRewardsModel {
    private String month1;
    private String month2;
    private String month3;
    private int total;
}
