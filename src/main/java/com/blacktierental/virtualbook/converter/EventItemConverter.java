package com.blacktierental.virtualbook.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.model.EventItem;
import com.blacktierental.virtualbook.service.EventItemService;

@Component
public class EventItemConverter implements Converter<Object, EventItem> {
	static final Logger logger = LoggerFactory.getLogger(EventItemConverter.class);
	
	@Autowired
	EventItemService eventItemService;

	@Override
	public EventItem convert(Object element) {
		if(element instanceof EventItem){
			return (EventItem)element;
		}else{
			Integer id = Integer.parseInt((String)element);
			EventItem eventItem = eventItemService.findById(id);
			logger.info("EventItem",eventItem);
			return eventItem;
		}
	}
	
	
	
}
