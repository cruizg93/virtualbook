package com.blacktierental.virtualbook.service;

import java.util.List;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.dao.EventItemDao;
import com.blacktierental.virtualbook.dao.ItemDao;
import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.model.State;

@Service("itemService")
@Transactional(noRollbackFor=TransactionException.class)
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao dao;

	@Autowired
	private EventItemDao eventItemDao;
	
	@Override
	public Item findById(int id) throws ObjectNotFoundException {
		return dao.findById(id);
	}

	@Override
	public Item findByDescription(String description) throws ObjectNotFoundException {
		return dao.findByDescription(description);
	}

	@Override
	public void saveItem(Item item) throws Exception {
		try {
			Item resultOfSearch = findByDescription(item.getDescription());
			if(resultOfSearch != null){
				resultOfSearch.setState(State.ACTIVE.toString());
				resultOfSearch.setQuantity(item.getQuantity());
				updateItem(resultOfSearch);
			}else{
				dao.save(item);
			}
		} catch (ObjectNotFoundException e) {
			dao.save(item);
		}catch (TransactionException e){
			throw new Exception("Somethig got wrong: </br>"+e.getMessage());
		}
	}

	@Override
	public void updateItem(Item item) throws ObjectNotFoundException {
		Item entity = dao.findById(item.getId());
		if(item!=null){
			entity.setDescription(item.getDescription());
			entity.setState(item.getState());
			entity.setQuantity(item.getQuantity());
			entity.setAttachments(item.getAttachments());
		}
	}

	@Override
	public void deleteItemByDescription(String description) throws ObjectNotFoundException {
		Item item = dao.findByDescription(description);
		List<EventItem> eventItems = eventItemDao.findByItemId(item);
		if(eventItems != null && !eventItems.isEmpty()){
			item.setState(State.DELETED.toString());
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
		try {
			Item item = findByDescription(description);
			if(item ==null){item=findById(id);}
			//if item exist but the state is deleted. it allowed to recreate the item
			if(item != null && item.getState().equals(State.DELETED.toString())){
				return true;
			}
			return (item == null || ((id!=null) && (item.getId()==id) ));
		} catch (ObjectNotFoundException e) {
			return true;
		}
	}
}
