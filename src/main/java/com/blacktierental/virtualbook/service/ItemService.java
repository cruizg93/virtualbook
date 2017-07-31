package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Item;

public interface ItemService {
	Item findById(int id) throws ObjectNotFoundException;
	Item findByDescription(String description) throws ObjectNotFoundException;
	
	void saveItem(Item item) throws ObjectNotFoundException, Exception;
	void updateItem(Item item) throws ObjectNotFoundException;
	void deleteItemByDescription(String description) throws ObjectNotFoundException;
	
	List<Item> findAllItems();
	boolean isItemDescriptionUnique(Integer id,String description) throws ObjectNotFoundException;
}
