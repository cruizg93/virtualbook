package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Location;

public interface LocationDao {

	Location findById(int id) throws ObjectNotFoundException;
	Location findByLocation(String location);
	Location findByBuildingName(String buildingName);
	void save(Location location);
	void deleteById(int id, Location location);
	void deleteByLocation(String location);
	List<Location> findAllLocations();
}
