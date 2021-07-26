package com.bus.bookingservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.bookingservice.model.BookTicket;
import com.bus.bookingservice.model.Bookings;
import com.bus.bookingservice.model.Bus;
import com.bus.bookingservice.model.GenericAPIResponse;
import com.bus.bookingservice.repository.BookingRepository;
import com.bus.bookingservice.repository.BusRepository;
import com.bus.bookingservice.model.ReversePayment;
import com.bus.bookingservice.model.TransferRequest;

import ch.qos.logback.classic.Logger;

@Service
public class BookingService {

	@Autowired
	BusRepository busRepo;

	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	PaymentProxy payment;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public List<Bus> getBusDetails(String from, String to) {
		List<Bus> buses = new ArrayList<>();
		try {
			buses = busRepo.findBySourceAndDestination(from, to);
		} catch (Exception e) {
			logger.error("error fetching bus with source destination {}", e.getMessage());
		}
		return buses;
	}

	public List<Bookings> getCustomerBookings(Integer custId) {
		List<Bookings> bookings = new ArrayList<>();
		try {
			bookings = bookingRepo.findByCustId(custId);
		} catch (Exception e) {
			logger.error("error fetching booking with customerid {}", e.getMessage());
		}
		return bookings;
	}

	public List<Bookings> getCustomerBookingsInRange(Integer custId, LocalDate from, LocalDate to) {
		List<Bookings> bookings = new ArrayList<>();
		try {
			bookings = bookingRepo.findByCustIdAndDateBetween(custId, from, to);
		} catch (Exception e) {
			logger.error("error fetching booking with customerid {}", e.getMessage());
		}
		return bookings;
	}

	public GenericAPIResponse bookTicket(BookTicket ticket) {
		GenericAPIResponse resp = new GenericAPIResponse();
	
		boolean payment = false;
		try {
			Double price;
			Optional<Bus> bus = busRepo.findById(ticket.getBusId());
			if (bus.isPresent()) {
				price = (bus.get().getPrice() * ticket.getQuantity());
				TransferRequest fundDeduction = new TransferRequest(ticket.getCustId(), 45, "Ebus Booking", price);
				payment = this.placeOrder(fundDeduction);
				if (payment) {
					Bookings booking = new Bookings(ticket.getCustId(), bus.get().getSource(), bus.get().getDestination(),
							LocalDate.now(), price);
					bookingRepo.save(booking);
					Integer updatedSeatVacant = (bus.get().getSeatVacant() - ticket.getQuantity());
					bus.get().setSeatVacant(updatedSeatVacant);
					busRepo.save(bus.get());
					resp.setMessage("successfuly booked your ticket");
					resp.setType("success");

					return resp;
				} else {
					resp.setMessage("couldnt book your ticket due to payment issue");
					resp.setType("error");
					return resp;
				}
			} else {
				resp.setMessage("couldnt book your ticket due to some issue");
				resp.setType("error");
				return resp;
			}
		} catch (Exception e) {
			if (payment) {
				Optional<Bus> bus = busRepo.findById(ticket.getBusId());
				if (bus.isPresent()) {
					Double price = (bus.get().getPrice() * ticket.getQuantity());
					logger.error("error adding order reversing payment{}", e.getStackTrace());
					ReversePayment reverse = new ReversePayment(ticket.getCustId(), price);
					this.reversePayment(reverse);
				}
			}
		}
		
		return resp;
	}

	private boolean placeOrder(TransferRequest fundDeduction) {
		try {
			payment.doPayment(fundDeduction);
			return true;
		} catch (Exception e) {
			logger.error("error processing payment {}", e.getMessage());
		}
		return false;

	}

	private void reversePayment(com.bus.bookingservice.model.ReversePayment reverse) {
		try {
			payment.reversePayment(reverse);

		} catch (Exception e) {
			logger.error("error reversing payment {}", e.getMessage());
		}

	}

	public void addBus(Bus bus) {
		busRepo.save(bus);
		
	}

}
