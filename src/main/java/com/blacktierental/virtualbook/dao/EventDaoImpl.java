package com.blacktierental.virtualbook.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Location;
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
	public List<Event> findByClient(Client client) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("client",client));
		List<Event> events = (List<Event>)crit.list();
		if(events != null){
			for(Event event : events){}
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
			for(Event event : events){}
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
		// TODO: validate when delete or just update state of an event
		/*if(true){
			//update state to unactive
		}else{
			//delete physical
		}*/
		delete(event);
	}

	@Override
	public List<Event> findAllEventsByDateRange(LocalDateTime initial, LocalDateTime end) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAndHour"));
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
}
