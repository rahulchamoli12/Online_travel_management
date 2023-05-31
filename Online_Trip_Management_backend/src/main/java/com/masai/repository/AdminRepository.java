package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
