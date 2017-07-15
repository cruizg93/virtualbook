package com.blacktierental.virtualbook.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.service.LocationService;

@Component
public class LocationConverter implements Converter<Object, Location>  {

	static final Logger logger = LoggerFactory.getLogger(LocationConverter.class);
	
	@Autowired
	LocationService locationService;
	
	/**
     * Gets Location by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	public Location convert(Object element){
		if(element instanceof Location){
			return (Location)element;
		}else{
			try {
				Integer id = Integer.parseInt((String)element);
				Location location= locationService.findById(id);
				logger.info("Location:",location);
				return location;
			} catch (Exception e) {
				Location location= locationService.findByLocation((String)element);
				logger.info("Location:",location);
				return location;
			}
		}
	}
}
