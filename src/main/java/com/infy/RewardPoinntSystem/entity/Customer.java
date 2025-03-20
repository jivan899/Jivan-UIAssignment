package com.infy.RewardPoinntSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;
    private String password; 

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<CustomerTransaction> transactions;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<RewardPoints> rewardPoints;


    
    

}