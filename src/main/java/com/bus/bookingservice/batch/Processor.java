package com.bus.bookingservice.batch;


import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus.bookingservice.model.Bus;
import com.bus.bookingservice.repository.BusRepository;


@Component
public class Processor implements ItemProcessor<Bus, Bus> {

	@Autowired
	private BusRepository busRepo;

	@Override
	public Bus process(Bus bus) throws Exception {
		Optional<Bus> busFromDb = busRepo.findById(bus.getId());
		if(busFromDb.isPresent()) {
			bus.setId((busFromDb.get().getId()));
		}
		return bus;
	}

	

}