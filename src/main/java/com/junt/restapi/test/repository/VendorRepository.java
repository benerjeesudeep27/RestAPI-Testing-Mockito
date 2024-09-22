package com.junt.restapi.test.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junt.restapi.test.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Serializable>{
	  
	 public List<Vendor> findAllByVendorName(String vendorName);
}
