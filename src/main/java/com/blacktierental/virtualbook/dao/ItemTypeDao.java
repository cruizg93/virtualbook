package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.model.ItemType;

public interface ItemTypeDao {

	List<ItemType> findAll();
	ItemType findByDescription(String description);
	ItemType findById(int id);
}
