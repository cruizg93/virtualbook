package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Location;

public interface LocationService {

	Location findById(int id) throws ObjectNotFoundException;
	Location findByLocation(String location);
	Location findByBuildingName(String buildingName);
	void save(Location location);
	void updateLocation(Location location) throws ObjectNotFoundException;
	void deleteByLocation(String location);
	void deleteById(int id) throws ObjectNotFoundException;
	List<Location> findAllLocations();
	
	boolean isLocationUnique(Integer id, String location);
	
}
