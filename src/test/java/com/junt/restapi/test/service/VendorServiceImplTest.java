package com.junt.restapi.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.junt.restapi.test.entity.Vendor;
import com.junt.restapi.test.repository.VendorRepository;

public class VendorServiceImplTest {

	private VendorService vendorService;
	
	@Mock
	private VendorRepository vendorRepository;
	
	private Vendor vendor;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		vendorService = new VendorServiceImpl(vendorRepository);
		vendor = new Vendor(101, "Flipkart", "Bangalore", "8520369741");
	}
	
	@Test
	public void testCreateVendor() {
		when(vendorRepository.save(any())).thenReturn(vendor);
		assertThat(vendorService.createVendor(vendor)).isEqualTo(true);
	}
	
	@Test
	public void testGetVendorDetailsById() {
		when(vendorRepository.findById(anyInt())).thenReturn(Optional.ofNullable(vendor));
		assertThat(vendorService.getVendorDetails(101).getVendorName()).isEqualTo(vendor.getVendorName());
	}
	
	@Test
	public void testGetVendorInfoByVendorName() {
		when(vendorRepository.findAllByVendorName(anyString())).thenReturn(new ArrayList<>(Collections.singleton(vendor)));
		assertThat(vendorService.getVendorInfoByVendorName("Flipkart").get(0).getVendorLocation()).isEqualTo(vendor.getVendorLocation());
	}
	
	@Test
	public void testGetAllVendors() {
		when(vendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(vendor)));
		assertThat(vendorService.getAllVendors().get(0).getVendorName()).isEqualTo(vendor.getVendorName());
	}

	@Test
	public void testDeleteVendor() {
		mock(VendorRepository.class, Mockito.CALLS_REAL_METHODS);
		doAnswer(Answers.CALLS_REAL_METHODS).when(vendorRepository).deleteById(anyInt());
		assertThat(vendorService.deleteVendorById(101)).isEqualTo("Vendor Deleted");
	}
}
