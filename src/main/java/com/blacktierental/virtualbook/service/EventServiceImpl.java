package com.blacktierental.virtualbook.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Location;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventItemService eventItemServiceImpl;
	
	@Autowired
	private EventDao dao;
	
	@Override
	public Event findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Event> findByClient(Client client) {
		return dao.findByClient(client);
	}

	@Override
	public List<Event> findByLocation(Location location) {
		return dao.findByLocation(location);
	}

	@Override
	public List<Event> findByDate(LocalDate date) {
		return dao.findByDate(date);
	}

	@Override
	public void save(Event event) {
		dao.save(event);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Event> findAllEventsByDateRange(LocalDate initial, LocalDate end) {
		LocalDateTime i = initial.atTime(0,0,0);
		LocalDateTime e = end.atTime(23, 59, 59);
		return dao.findAllEventsByDateRange(i, e);
	}

	@Override
	public List monthlyCountByYear(int year){
		return dao.findAllByYearGroupByMonth(year);
	}
	
	@Override
	public List clientCountByYear(int year){
		return dao.findEventByYearGroupByClient(year);
	}
	
	@Override
	public List<Event> findAllUpComingEvent() {
		return dao.findAllUpComingEvent();
	}

	@Override
	public void updateEvent(Event event) {
		Event entity = dao.findById(event.getId());
		if(entity != null){
			entity.setAdvance(event.getAdvance());
			entity.setClient(event.getClient());
			entity.setComments(event.getComments());
			entity.setContactSameAsClient(event.getContactSameAsClient());
			entity.setContactPersonEmail(event.getContactPersonEmail());
			entity.setContactPersonName(event.getContactPersonName());
			entity.setContactPersonPhoneNumber(event.getContactPersonPhoneNumber());
			entity.setDateAndHour(event.getDateAndHour());
			entity.setDelivery(event.getDelivery());
			entity.setEventName(event.getEventName());
			entity.setLocation(event.getLocation());
			entity.setTaxPercentage(event.getTaxPercentage());
			entity.setItems(event.getItems());
			System.out.println("");
		}
	}

	@Override
	public boolean isEventUnique(Event event) {
		return true;
	}
}
