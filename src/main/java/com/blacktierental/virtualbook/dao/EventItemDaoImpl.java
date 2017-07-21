package com.blacktierental.virtualbook.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;

@Repository("EventItemDao")
public class EventItemDaoImpl extends AbstractDao<Integer, EventItem> implements EventItemDao {

	static final Logger logger = LoggerFactory.getLogger(EventItemDaoImpl.class);
	
	@Override
	public EventItem findById(int id) {
		EventItem eventItem = getByKey(id);
		return eventItem;
	}

	@Override
	public void save(EventItem eventItem) {
		persist(eventItem);
	}

	@Override
	public List<EventItem> findAllEventItems() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<EventItem> eventItems =  (List<EventItem>)criteria.list();
		return eventItems;
	}



	@Override
	public List<EventItem> findByEventId(Event event) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<EventItem> findByItemId(Item item) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("item",item));
		return (List<EventItem>)crit.list();
	}

	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id",id));
		EventItem ei = (EventItem)crit.uniqueResult();
		// TODO: validate when delete or just update state of an event
		/*if(true){
			//update state to unactive
		}else{
			//delete physical
		}*/
		delete(ei);
	}

	@Override
	public List findAllByYearGroupByItem(int year) {
		Query q = getSession().createQuery("SELECT i.description, count(ei.id)" 
										+" FROM EventItem ei, Item i, Event e"
										+" WHERE DATE_FORMAT(e.dateAndHour,'%Y')=? and ei.event = e.id and i.id=ei.item AND e.state ='ACTIVE'"
										+" GROUP BY ei.item");
		q.setParameter(0, String.valueOf(year));
		return q.getResultList();
	}

}
