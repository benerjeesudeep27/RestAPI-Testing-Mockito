package com.junt.restapi.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junt.restapi.test.entity.Vendor;
import com.junt.restapi.test.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;
	
	public VendorServiceImpl(VendorRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean createVendor(Vendor vendor) {
		Vendor ven = repository.save(vendor);
		if(ven != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Vendor getVendorDetails(int vendorId) {
		Vendor vendor2 = null;
		Optional<Vendor> vendor = repository.findById(vendorId);
		if(vendor.isPresent()) {
			 vendor2 = vendor.get(); 
		}
		return vendor2;
	}

	@Override
	public List<Vendor> getVendorInfoByVendorName(String vendorName) {
		List<Vendor> vendorList = repository.findAllByVendorName(vendorName);
		return vendorList;
	}

	@Override
	public List<Vendor> getAllVendors() {
		List<Vendor> vendorList = repository.findAll();
		return vendorList;

	}

	@Override
	public String deleteVendorById(int vendorId) {
		repository.deleteById(vendorId);
		return "Vendor Deleted";
	}

}
