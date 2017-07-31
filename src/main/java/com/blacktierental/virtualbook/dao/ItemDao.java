package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Item;

public interface ItemDao {

	Item findById(int id) throws ObjectNotFoundException;
	Item findByDescription(String description) throws ObjectNotFoundException;
	void save(Item item);
	void deleteByDescription(String description, Item item);
	List<Item> findAllItems();
}
