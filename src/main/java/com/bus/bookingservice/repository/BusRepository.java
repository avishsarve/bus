package com.bus.bookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.bookingservice.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

	List<Bus> findBySourceAndDestination(String from, String to);

}
