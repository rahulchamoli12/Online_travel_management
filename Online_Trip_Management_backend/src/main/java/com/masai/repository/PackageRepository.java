package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Package;

public interface PackageRepository extends JpaRepository<Package, Integer>{

}
