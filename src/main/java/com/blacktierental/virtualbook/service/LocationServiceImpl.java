package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.LocationDao;
import com.blacktierental.virtualbook.model.Location;


@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	private LocationDao dao;

	@Override
	public Location findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Location findByLocation(String location) {
		return dao.findByLocation(location);
	}

	@Override
	public Location findByBuildingName(String buildingName) {
		return dao.findByBuildingName(buildingName);
	}

	@Override
	public void save(Location location) {
		dao.save(location);
	}

	@Override
	public void updateLocation(Location location) {
		Location entity = dao.findById(location.getId());
		if(entity !=null){
			entity.setBuildingName(location.getBuildingName());
			entity.setLocation(location.getLocation());
			entity.setPhoneNumber(location.getLocation());
		}
	}

	@Override
	public void deleteByLocation(String location) {
		dao.deleteByLocation(location);
	}

	@Override
	public List<Location> findAllLocations() {
		return dao.findAllLocations();
	}

	@Override
	public boolean isLocationUnique(Integer id, String location) {
		Location locationObj = findByLocation(location);
		return (locationObj == null || ((id != null) && (locationObj.getId()==id)));
	}
}
