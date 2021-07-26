package com.bus.bookingservice.model;

import java.time.LocalDate;

public class TransferRequest {
	
	private Integer customerId;
	
	private Integer beneficiaryId;
	
	private String beneficiaryName;
	
	private Double amount;
	
	public TransferRequest() {}

	public TransferRequest(Integer customerId, Integer beneficiaryId, String beneficiaryName, Double amount) {
		super();
		this.customerId = customerId;
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryName = beneficiaryName;
		this.amount = amount;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	

}
