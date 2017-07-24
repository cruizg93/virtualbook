package com.blacktierental.virtualbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventItemDao;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;

@Service("eventItemService")
@Transactional
public class EventItemServiceImpl implements EventItemService {

	@Autowired
	private EventItemDao dao;
	
	@Autowired
	private ItemService itemService;
	
	@Override
	public EventItem findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<EventItem> findByEventId(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventItem> findByItemId(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEventItem(EventItem eventItem) {
		dao.save(eventItem);
	}

	@Override
	public void updateEventItem(EventItem eventItem) {
		EventItem entity = dao.findById(eventItem.getId());
		if(entity != null){
			entity.setEvent(eventItem.getEvent());
			entity.setItem(eventItem.getItem());
			entity.setComment(eventItem.getComment());
			entity.setPricePerUnit(eventItem.getPricePerUnit());
			entity.setQuantity(eventItem.getQuantity());
		}
	}

	@Override
	public List<EventItem> findAllEventItems() {
		return dao.findAllEventItems();
	}	
		
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<String[]> itemCountByYear(int year) {
		return dao.findAllByYearGroupByItem(year);
	}

	@Override
	public List<EventItem> onePerItem() {
		List<Item> items = itemService.findAllItems();
		List<EventItem> eventItems = new ArrayList<EventItem>();
		for(Item i: items){
			eventItems.add(new EventItem(i,0,0.0));
		}
		return eventItems;
	}
	
	
	
}
