package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Admin;
import com.masai.entity.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByEmail(String email);
}
