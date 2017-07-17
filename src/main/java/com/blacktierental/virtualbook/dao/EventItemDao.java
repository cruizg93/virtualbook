package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;

public interface EventItemDao {
	
	EventItem findById(int id);
	List<EventItem> findByEventId(Event event);
	List<EventItem> findByItemId(Item item);
	List<EventItem> findAllEventItems();
	void save(EventItem eventItem);
	void deleteById(int id);
	List findAllByYearGroupByItem(int year);
}
