package com.blacktierental.virtualbook.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Location;

public interface EventDao {

	Event findById(int id) throws ObjectNotFoundException;
	List<Event> findByClient(Client client);
	List<Event> findByLocation(Location location);
	List<Event> findByItem(EventItem items);
	List<Event> findByDate(LocalDate date);
	List<Event> findIncompleteEvents();
	List<Event> findAllEventsByDateRange(LocalDateTime initial, LocalDateTime end);
	List findAllByYearGroupByMonth(int year);
	List<Event> findAllUpComingEvent();
	void save(Event event);
	void deleteById(int id);
	List findEventByYearGroupByClient(int year);
	Event findByDateNClientNLocation(Event event) throws ObjectNotFoundException;
	
}
