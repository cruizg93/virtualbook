package com.blacktierental.virtualbook.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Location;

public interface EventService {

	Event findById(int id);
	boolean isEventUnique(Event event);
	List<Event> findByClient(Client client);
	List<Event> findByLocation(Location location);
	List<Event> findByDate(LocalDate date);
	void save(Event event);
	void updateEvent(Event event);
	void deleteById(int id);
	List<Event> findAllEventsByDateRange(LocalDate initial, LocalDate end);
	List monthlyCountByYear(int year);
	List<Event> findAllUpComingEvent();
	List<String[]> clientCountByYear(int year);
}
