package com.blacktierental.virtualbook.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Location;

public interface EventDao {

	Event findById(int id);
	List<Event> findByClient(Client client);
	List<Event> findByLocation(Location location);
	List<Event> findByDate(LocalDate date);
	void save(Event event);
	void deleteById(int id);
	List<Event> findAllEventsByDateRange(LocalDateTime initial, LocalDateTime end);
	List<Event> findAllUpComingEvent();
}
