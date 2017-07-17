package com.blacktierental.virtualbook.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.model.Event;

@Service("statsService")
@Transactional
public class StatsServiceImpl implements StatsService{

	@Autowired
	EventService eventService;

	@Autowired
	EventItemService eventItemService;
	
	@Override
	public List<String[]> monthlyCountByYear(int year) {
		List<String[]> fullYear = new ArrayList();
		List<String[]> byMonths = eventService.monthlyCountByYear(year);
		for(int i=1;i<=12;i++){
			if(!byMonths.isEmpty()){
				Object[] month = byMonths.get(0);
				String monthLabel = String.valueOf(month[0]).toUpperCase();
				if(Month.of(i).name().equals(monthLabel)){
					fullYear.add(new String[]{monthLabel,String.valueOf(month[1])});
					if(!byMonths.isEmpty()){
						byMonths.remove(0);
					}
				}else{
					fullYear.add(new String[]{Month.of(i).name(),"0"});
				}
			}else{
				fullYear.add(new String[]{Month.of(i).name(),"0"});
			}
		}
		return fullYear;
	}	

	@Override
	public List clientsByYear(int year) {
		return eventService.clientCountByYear(year);
	}
	
	@Override
	public List itemsByYear(int year){
		return eventItemService.itemCountByYear(year);
	}
	

}
