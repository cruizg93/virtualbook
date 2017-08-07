package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;

public interface EventItemService {
	EventItem findById(int id);
	List<EventItem> findByEventId(Event event);
	List<EventItem> findByItemId(Item item);
	void saveEventItem(EventItem eventItem);
	void updateEventItem(EventItem eventItem);
	void deleteById(int id);
	List<EventItem> findAllEventItems();
	List<String[]> itemCountByYear(int year);
	List<EventItem> onePerItem();
	
}
