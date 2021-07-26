package com.bus.bookingservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.bookingservice.model.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Integer> {

	List<Bookings> findByCustId(Integer custId);

	List<Bookings> findByCustIdAndDateBetween(Integer custId, LocalDate from, LocalDate to);

}
