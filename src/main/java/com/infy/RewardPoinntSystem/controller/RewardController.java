package com.infy.RewardPoinntSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.RewardPoinntSystem.entity.RewardPoints;
import com.infy.RewardPoinntSystem.serviceImpl.RewardServiceImpl;

@RestController
@RequestMapping("/rewards")
public class RewardController {
    @Autowired
    private RewardServiceImpl rewardService;

    @GetMapping("/customer/{customerId}")
    public List<RewardPoints> getRewardPointsByCustomerId(@PathVariable Long customerId) {
        return rewardService.getRewardPointsByCustomerId(customerId);
    }
    
    
    @GetMapping("/")
    public List<RewardPoints> getAllRewardPoints(){
    	return  rewardService.getAllRewardPoints();
    }
}