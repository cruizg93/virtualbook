package com.blacktierental.virtualbook.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.ItemType;

@Repository("itemTypeDao")
public class ItemTypeDaoImpl extends AbstractDao<Integer,ItemType> implements ItemTypeDao {
	
	@SuppressWarnings("unchecked")
	public List<ItemType> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<ItemType>)crit.list();
	}

	public ItemType findByDescription(String description) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("description",description));
		return (ItemType)crit.uniqueResult();
	}

	public ItemType findById(int id) {
		return getByKey(id);
	}
}
