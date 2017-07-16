package com.blacktierental.virtualbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.dao.EventItemDao;
import com.blacktierental.virtualbook.dao.ItemDao;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;

import sun.net.ProgressSource.State;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao dao;

	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private EventItemDao eventItemDao;
	
	@Override
	public Item findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Item findByDescription(String description) {
		return dao.findByDescription(description);
	}

	@Override
	public void saveItem(Item item) {
		dao.save(item);
	}

	@Override
	public void updateItem(Item item) {
		Item entity = dao.findById(item.getId());
		if(item!=null){
			entity.setDescription(item.getDescription());
			entity.setItemTypes(item.getItemTypes());
			entity.setState(item.getState());
			entity.setQuantity(item.getQuantity());
		}
	}

	@Override
	public void deleteItemByDescription(String description) {
		Item item = dao.findByDescription(description);
		List<EventItem> eventItems = eventItemDao.findByItemId(item);
		if(eventItems != null && !eventItems.isEmpty()){
			item.setState(State.DELETE.toString());
			dao.save(item);
		}else{
			dao.deleteByDescription(description, item);
		}
	}

	@Override
	public List<Item> findAllItems() {
		return dao.findAllItems();
	}

	@Override
	public boolean isItemDescriptionUnique(Integer id, String description) {
		Item item = findByDescription(description);
		return (item == null || ((id!=null) && (item.getId()==id)));
	}

	@Override
	public List<Item> findMainItems() {
		return dao.findMainItems();
	}

	@Override
	public List<Item> findAttachedItems() {
		return dao.findAttachedItems();
	}
	
	
}
