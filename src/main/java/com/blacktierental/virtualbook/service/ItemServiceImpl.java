package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.ItemDao;
import com.blacktierental.virtualbook.model.Item;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao dao;

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
		dao.deleteByDescription(description);
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
