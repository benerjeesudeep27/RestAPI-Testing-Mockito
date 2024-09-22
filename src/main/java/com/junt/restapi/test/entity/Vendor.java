package com.junt.restapi.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendorId;
	@Column(name = "vendorName", nullable = false)
	private String vendorName;
	@Column(name = "vendorLocation", nullable = false)
	private String vendorLocation;
	@Column(name = "vendorContactNo", nullable = false)
	private String vendorContactNo;
	
	public Vendor(int vendorId, String vendorName, String vendorLocation, String vendorContactNo) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorLocation = vendorLocation;
		this.vendorContactNo = vendorContactNo;
	}
	
	public Vendor() {

	}

	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorLocation() {
		return vendorLocation;
	}
	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}
	public String getVendorContactNo() {
		return vendorContactNo;
	}
	public void setVendorContactNo(String vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}
	
}
