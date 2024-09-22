package com.junt.restapi.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.junt.restapi.test.entity.Vendor;
import com.junt.restapi.test.service.VendorService;

@RestController
@RequestMapping("/vendors") // Base URl
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/addvendor")
	public String addNewVendor(@RequestBody Vendor vendor) {
		boolean status = vendorService.createVendor(vendor);
		if (status)
			return "Vendor Added Successfully";
		else
			return "Vendor Adding Failed";

	}

	@GetMapping("/allvendors/{id}")
	public Vendor getVendorDetailsById(@PathVariable("id") int vendorId) {
		Vendor vendorDetails = vendorService.getVendorDetails(vendorId);
		return vendorDetails;

	}

	@GetMapping("/vendorbyname/{name}")
	public List<Vendor> getVendorDetailsByName(@PathVariable("name") String name) {
		List<Vendor> vendorList = vendorService.getVendorInfoByVendorName(name);
		return vendorList;
	}

	@GetMapping("/allvendorsdetails")
	public List<Vendor> getAllVendors() {
		List<Vendor> vendorList = vendorService.getAllVendors();
		return vendorList;
	}

	@DeleteMapping("/deletevendor/{id}")
	public String deleteVendor(@PathVariable("id") int id) {
		String message = "";
		String result = vendorService.deleteVendorById(id);
		if (result.equals("Vendor Deleted")) {
			return "Vendor is deleted successfully";
		} else {
			return "Vendor could not be deleted";
		}
	}
}
