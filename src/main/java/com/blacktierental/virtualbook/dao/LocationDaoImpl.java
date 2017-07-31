package com.blacktierental.virtualbook.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.model.State;

@Repository("locationDao")
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements LocationDao{

	static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

	@Autowired
	EventDao eventDao;
	
	@Override
	public Location findById(int id) throws ObjectNotFoundException {
		Location location = getByKey(id);
		if(location==null){
			throw new ObjectNotFoundException("Location with id: "+id+" was not found");
		}
		return location;
	}

	@Override
	public Location findByLocation(String location) {
		System.out.println("Location "+location);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location",location));
		Location locationObj = (Location)crit.uniqueResult();
		return locationObj;
	}

	@Override
	public Location findByBuildingName(String buildingName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("buildingName",buildingName));
		Location location = (Location)crit.uniqueResult();
		return location;
	}

	@Override
	public void save(Location location) {
		persist(location);
	}

	@Override
	public void deleteByLocation(String location) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("location",location));
		Location locationObj = (Location)criteria.uniqueResult();
		delete(locationObj);
	}
	@Override
	public List<Location> findAllLocations() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("state",State.ACTIVE.toString()));
		criteria.addOrder(Order.asc("buildingName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Location> locations = (List<Location>)criteria.list();
		return locations;
	}

	/**
	 * IF param location is null
	 * look for a location with id equals to param id
	 */
	@Override
	public void deleteById(int id, Location location) {
		if(location == null){
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("id",id));
			location = (Location) criteria.uniqueResult();
		}
		delete(location);
	}
}
