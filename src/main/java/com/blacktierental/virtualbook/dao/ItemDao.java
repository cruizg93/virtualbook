package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.model.Item;

public interface ItemDao {

	Item findById(int id);
	Item findByDescription(String description);
	void save(Item item);
	void deleteByDescription(String description);
	List<Item> findAllItems();
}
