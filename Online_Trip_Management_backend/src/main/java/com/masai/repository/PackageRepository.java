package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Package;

public interface PackageRepository extends JpaRepository<Package, Integer>{
	
	public Package findByPackageName(String packageName); 
	
}
