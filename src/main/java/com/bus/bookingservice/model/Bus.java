package com.bus.bookingservice.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String source;
	private String destination;
	private String vendor;
	private String arrival;
	private String departure;
	private Double price;
	private Integer seatVacant;
	
	public Bus() {}
	
	public Bus(String source, String destination, String vendor, String arrival, String departure, Double price,
			Integer seatVacant) {
		super();
		this.source = source;
		this.destination = destination;
		this.vendor = vendor;
		this.arrival = arrival;
		this.departure = departure;
		this.price = price;
		this.seatVacant = seatVacant;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSeatVacant() {
		return seatVacant;
	}
	public void setSeatVacant(Integer seatVacant) {
		this.seatVacant = seatVacant;
	}
	
	
}
