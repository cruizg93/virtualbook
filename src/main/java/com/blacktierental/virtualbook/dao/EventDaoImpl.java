package com.blacktierental.virtualbook.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.model.State;
import com.blacktierental.virtualbook.model.User;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	static final Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);
	
	@Override
	public Event findById(int id) {
		Event event = getByKey(id);
		if(event !=null){
			//Hibernate.initialize(event.getItems());
		}
		return event;
	}

	@Override
	public List<Event> findByItem(EventItem items) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("items",items));
		return (List<Event>)crit.list();
	}
	
	@Override
	public List<Event> findByClient(Client client) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("client",client));
		List<Event> events = (List<Event>)crit.list();
		if(events != null){
			//for(Event event : events){}
			//Hibernate.initialize(event.getItems());
		}
		return events;
	}

	@Override
	public List<Event> findByLocation(Location location) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location",location));
		List<Event> events = (List<Event>)crit.list();
		if(events != null){
			//for(Event event : events){}
			//Hibernate.initialize(event.getItems());
		}
		return events;
	}

	@Override
	public List<Event> findByDate(LocalDate date) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("date",date));
		List<Event> events = (List<Event>)crit.list();
		if(events != null){
			for(Event event : events){}
			//Hibernate.initialize(event.getItems());
		}
		return events;
	}

	@Override
	public void save(Event event) {
		persist(event);
	}

	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id",id));
		Event event = (Event)crit.uniqueResult();
		event.setState(State.DELETED.toString());
		save(event);
		//delete(event);
	}

	@Override
	public List<Event> findAllEventsByDateRange(LocalDateTime initial, LocalDateTime end) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAndHour"));
		criteria.add(Restrictions.eq("state",State.ACTIVE.toString()));
		criteria.add(Restrictions.between("dateAndHour", initial, end));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Event> events= (List<Event>)criteria.list();
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
		return events;
	}

	@Override
	public List<Event> findAllUpComingEvent() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("date"));
		//criteria.add(Restrictions.;/
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Event> events= (List<Event>)criteria.list();
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
		return events;
	}

	@Override
	public List findAllByYearGroupByMonth(int year) {
		Query q = getSession().createQuery("SELECT DATE_FORMAT(dateAndHour, '%M')as months, count(id) as count "
									+" FROM Event "
									+" WHERE DATE_FORMAT(dateAndHour,'%Y')=? "
									+ "GROUP BY DATE_FORMAT(dateAndHour, '%M')"
									+ " ORDER BY DATE_FORMAT(dateAndHour, '%M') DESC");
		q.setParameter(0,String.valueOf(year));
		List result = q.getResultList();
		return result;

	}
	
	@Override
	public List findEventByYearGroupByClient(int year){
		Query q = getSession().createQuery("SELECT c.name, count(e.id) " 
									+"FROM Event e, Client c " 
									+"WHERE DATE_FORMAT(e.dateAndHour,'%Y')=? AND e.client = c.id " 
									+"GROUP BY e.client");
		q.setParameter(0, String.valueOf(year));
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}