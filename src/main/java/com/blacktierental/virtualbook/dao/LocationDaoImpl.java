package com.blacktierental.virtualbook.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Location;

@Repository("locationDao")
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements LocationDao{

	static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

	@Override
	public Location findById(int id) {
		Location location = getByKey(id);
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
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("location"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Location> locations = (List<Location>)criteria.list();
		return locations;
	}
	
	
}
