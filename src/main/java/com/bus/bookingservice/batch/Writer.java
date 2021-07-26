package com.bus.bookingservice.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bus.bookingservice.model.Bus;
import com.bus.bookingservice.repository.BusRepository;


@Component
public class Writer implements ItemWriter<Bus>{
	
	@Autowired
	private BusRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Bus> bus) throws Exception {
		repo.saveAll(bus);
	}
	
}
