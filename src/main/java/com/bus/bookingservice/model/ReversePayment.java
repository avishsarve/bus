package com.bus.bookingservice.model;

public class ReversePayment {
	
	private Integer customerId;
	
	private Double price;

	public ReversePayment() {};
	
	
	public ReversePayment(Integer customerId, Double price) {
		super();
		this.customerId = customerId;
		this.price = price;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
