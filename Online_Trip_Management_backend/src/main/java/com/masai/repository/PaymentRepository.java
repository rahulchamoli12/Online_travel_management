package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer>{

}
