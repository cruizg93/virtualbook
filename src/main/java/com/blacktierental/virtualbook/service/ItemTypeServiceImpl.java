package com.blacktierental.virtualbook.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.ItemTypeDao;
import com.blacktierental.virtualbook.model.ItemType;

@Service("itemTypeService")
@Transactional
public class ItemTypeServiceImpl implements ItemTypeService{
	@Autowired
	ItemTypeDao dao;

	@Override
	public ItemType findById(int id) {
		return dao.findById(id);
	}

	@Override
	public ItemType findByDescription(String description) {
		return dao.findByDescription(description);
	}

	@Override
	public List<ItemType> findAll() {
		return dao.findAll();
	}
	
	
}
