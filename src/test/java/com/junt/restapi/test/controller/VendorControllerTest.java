package com.junt.restapi.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.junt.restapi.test.entity.Vendor;
import com.junt.restapi.test.service.VendorService;

@WebMvcTest(controllers = VendorController.class)
public class VendorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VendorService vendorService;
	
	private Vendor vendor1;
	
	private Vendor vendor2;
	
	private List<Vendor> vendorList = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		vendor1 = new Vendor(1, "Flipkart", "Shimla", "7845120369");
		vendor2 = new Vendor(2, "Rapido", "Delhi", "8520369741");
		
		vendorList.add(vendor1);
		vendorList.add(vendor2);
	}
	
	@AfterEach
	public void tearDown() {
		vendorList.clear();
		vendor1 = null;
		vendor2 = null;
	}
	
	//Important Test Method
	@Test
	public void testAddNewVendor() throws Exception {ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter writer =  om.writer().withDefaultPrettyPrinter();
		String reqJson = writer.writeValueAsString(vendor1);
		when(vendorService.createVendor(any())).thenReturn(true);
		 
		mockMvc.perform(post("/vendors/addvendor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqJson))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetVendorDetailsById() throws Exception {
		when(vendorService.getVendorDetails(anyInt())).thenReturn(vendor2);
		
		mockMvc.perform(get("/vendors/allvendors/2")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetVendorDetailsByName() throws Exception {
		when(vendorService.getVendorInfoByVendorName(anyString())).thenReturn(vendorList);
		
		mockMvc.perform(get("/vendors/vendorbyname/Rapido")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllVendors() throws Exception {
		when(vendorService.getAllVendors()).thenReturn(vendorList);
		
		mockMvc.perform(get("/vendors/allvendorsdetails")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteVendor() throws Exception {
		when(vendorService.deleteVendorById(anyInt())).thenReturn("Vendor is deleted successfully");
		
		mockMvc.perform(delete("/vendors/deletevendor/1")).andDo(print()).andExpect(status().isOk());
	}
}
