package com.bus.bookingservice.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Qualifier;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bookingservice.model.BookTicket;
import com.bus.bookingservice.model.Bookings;
import com.bus.bookingservice.model.Bus;
import com.bus.bookingservice.model.GenericAPIResponse;
import com.bus.bookingservice.service.BookingService;

@RestController
@RequestMapping(value = "/bus")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("busJob")
    Job busKeeperJob;

	@GetMapping(value = "/{from}/{to}")
	public ResponseEntity<List<Bus>> getBusDetails(@PathVariable("from") String from, @PathVariable("to") String to) {
		return new ResponseEntity<List<Bus>>(bookingService.getBusDetails(from, to), HttpStatus.OK);

	}

	@GetMapping(value = "/{custId}")
	public ResponseEntity<List<Bookings>> getCustomerBookings(@PathVariable("custId") Integer custId) {
		return new ResponseEntity<List<Bookings>>(bookingService.getCustomerBookings(custId), HttpStatus.OK);

	}

	@GetMapping(value = "/{custId}/booking/{from}/{to}")
	public ResponseEntity<List<Bookings>> getCustomerBookingsInDateRange(@PathVariable("custId") Integer custId,
			@PathVariable("from") String from, @PathVariable("to") String to) {
		LocalDate fromDate = LocalDate.parse(from);
		LocalDate toDate = LocalDate.parse(to);

		return new ResponseEntity<List<Bookings>>(bookingService.getCustomerBookingsInRange(custId, fromDate, toDate), HttpStatus.OK);

	}

	@PostMapping(value = "/book")
	public ResponseEntity<GenericAPIResponse> bookTicket(@RequestBody BookTicket ticket) {
		return new ResponseEntity<GenericAPIResponse>(bookingService.bookTicket(ticket),HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public void addBus(@RequestBody Bus bus) {
		bookingService.addBus(bus);
	}
	
	 
	    
	    @GetMapping("/run-batch-job")
	    public String handle() throws Exception {
	 
	            JobParameters jobParameters = new JobParametersBuilder()
	            		.addDate("date", new Date())
	                    .addLong("time",System.currentTimeMillis()).toJobParameters();
	            jobLauncher.run(busKeeperJob, jobParameters);
	            
	        return "Batch job has been invoked";
	    }

}
