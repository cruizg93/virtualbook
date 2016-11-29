package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.model.Location;

public interface LocationDao {

	Location findById(int id);
	Location findByLocation(String location);
	Location findByBuildingName(String buildingName);
	void save(Location location);
	void deleteByLocation(String location);
	List<Location> findAllLocations();
}
