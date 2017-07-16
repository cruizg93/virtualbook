package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.model.Location;

public interface LocationService {

	Location findById(int id);
	Location findByLocation(String location);
	Location findByBuildingName(String buildingName);
	void save(Location location);
	void updateLocation(Location location);
	void deleteByLocation(String location);
	void deleteById(int id);
	List<Location> findAllLocations();
	
	boolean isLocationUnique(Integer id, String location);
	
}
