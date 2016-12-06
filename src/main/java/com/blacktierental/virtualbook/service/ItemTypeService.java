package com.blacktierental.virtualbook.service;

import java.util.List;
import com.blacktierental.virtualbook.model.ItemType;

public interface ItemTypeService {

	ItemType findById(int id);
	ItemType findByDescription(String description);
	List<ItemType> findAll();
}
