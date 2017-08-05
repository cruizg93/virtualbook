package com.blacktierental.virtualbook.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.service.EventService;

@Component
public class EventConververter implements Converter<Object, Event>{

	@Autowired
	EventService eventService;
	
	@Override
	public Event convert(Object element) {
		if(element instanceof Event){
			return (Event)element;
		}else{
			try {
				Integer id = Integer.parseInt((String)element);
				Event event = eventService.findById(id);
				return event;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
