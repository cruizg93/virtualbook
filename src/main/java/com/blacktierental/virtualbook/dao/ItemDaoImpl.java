package com.blacktierental.virtualbook.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Item;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer,Item>implements ItemDao{

	static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

	@Override
	public Item findById(int id) {
		Item item = getByKey(id);
		if(item != null){
			Hibernate.initialize(item.getItemTypes());
		}
		return item;
	}

	@Override
	public Item findByDescription(String description) {
		System.out.println("Description:"+description);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("description",description));
		Item item = (Item)crit.uniqueResult();
		if(item != null){
			Hibernate.initialize(item.getItemTypes());
		}
		return item;
	}

	@Override
	public void save(Item item) {
		persist(item);
	}

	@Override
	public void deleteByDescription(String description) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("description",description));
		Item item = (Item)crit.uniqueResult();
		delete(item);
	}

	@Override
	public List<Item> findAllItems() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("description"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>)criteria.list();
		return items;
	}
}
