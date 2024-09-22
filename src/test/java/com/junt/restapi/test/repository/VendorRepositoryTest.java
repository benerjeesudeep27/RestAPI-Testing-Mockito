package com.junt.restapi.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.junt.restapi.test.entity.Vendor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendorRepositoryTest {

	@Autowired
	private VendorRepository vendorRepository;
	
	private Vendor vendor;
	
	@BeforeEach
	public void setUp() {
		vendor = new Vendor(101, "Uber", "Shimla", "7845120369");
		Vendor saveVendorDetails = vendorRepository.save(vendor);
		System.out.println("Vendor Details : "+ saveVendorDetails);
	}
	
	@Test
	@DisplayName("Find All The Vendors By Name - Found")
	public void testFindAllVendorsByName() {
		List<Vendor> vendorList = vendorRepository.findAllByVendorName("Uber");
		assertThat(vendorList.get(0).getVendorName().equals(vendor.getVendorName()));
		assertThat(vendorList.get(0).getVendorLocation().equals(vendor.getVendorLocation()));
		assertThat(vendorList.get(0).getVendorContactNo().equals(vendor.getVendorContactNo()));
		
	}
	
	@Test
	@DisplayName("Find All the Vendors By Name - Not Found")
	public void testFindAllVendorsByName_NotFound() {
		List<Vendor> vendorList = vendorRepository.findAllByVendorName("Ola");
		assertThat(vendorList.isEmpty()).isTrue();
	}
	
	@AfterEach
	public void tearDown() {
		vendor = null;
		vendorRepository.deleteAll();
	}
}
