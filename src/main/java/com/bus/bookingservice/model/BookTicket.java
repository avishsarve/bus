package com.bus.bookingservice.model;

public class BookTicket {

	
	private Integer custId;
	private Integer busId;
	private Integer quantity;
	
	public BookTicket() {}
	
	public BookTicket(Integer custId, Integer busId, Integer quantity) {
		super();
		this.custId = custId;
		this.busId = busId;
		this.quantity = quantity;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
