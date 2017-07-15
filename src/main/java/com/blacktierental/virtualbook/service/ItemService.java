package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.model.Item;

public interface ItemService {
	Item findById(int id);
	Item findByDescription(String description);
	
	void saveItem(Item item);
	void updateItem(Item item);
	void deleteItemByDescription(String description);
	
	List<Item> findAllItems();
	List<Item> findMainItems();
	List<Item> findAttachedItems();
	boolean isItemDescriptionUnique(Integer id,String description);
}
