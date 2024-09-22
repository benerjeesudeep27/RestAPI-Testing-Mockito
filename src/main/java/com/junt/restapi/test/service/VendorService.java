package com.junt.restapi.test.service;

import java.util.List;

import com.junt.restapi.test.entity.Vendor;

public interface VendorService {

	public boolean createVendor(Vendor vendor);
	
	public Vendor getVendorDetails(int vendorId);
	
	public List<Vendor> getVendorInfoByVendorName(String vendorName);
	
	public List<Vendor> getAllVendors();
	
	public String deleteVendorById(int vendorId);
}
