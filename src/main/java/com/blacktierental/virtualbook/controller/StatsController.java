package com.blacktierental.virtualbook.controller;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blacktierental.virtualbook.service.StatsService;

@Controller
public class StatsController {

	@Autowired
	StatsService statsService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping( value = {"/dashboard"}, method=RequestMethod.GET)
	public String dashboard(ModelMap model){	
		return "dashboard";
	}
	
	@RequestMapping( value = {"/yearStats-{year}"}, method=RequestMethod.GET)
	public @ResponseBody String year(@PathVariable int year){	
		List<String[]> months = statsService.monthlyCountByYear(year);
		JSONArray yearJson = new JSONArray();
		for(String[] month: months){
			JSONObject json = new JSONObject();
			json.put("label",month[0]);
			json.put("value",month[1]);
			yearJson.put(json);
		}
		return yearJson.toString();
	}

	@RequestMapping( value = {"/clientStats-{year}"}, method=RequestMethod.GET)
	public @ResponseBody String clients(@PathVariable int year){	
		List<String[]> clients = statsService.clientsByYear(year);
		JSONArray yearJson = new JSONArray();
		for(Object[] client: clients){
			JSONObject json = new JSONObject();
			json.put("label",client[0]);
			json.put("value",client[1]);
			yearJson.put(json);
		}
		return yearJson.toString();
	}
	
	@RequestMapping( value = {"/itemStats-{year}"}, method=RequestMethod.GET)
	public @ResponseBody String items(@PathVariable int year){	
		List<String[]> clients = statsService.itemsByYear(year);
		JSONArray yearJson = new JSONArray();
		for(Object[] client: clients){
			JSONObject json = new JSONObject();
			json.put("label",client[0]);
			json.put("value",client[1]);
			yearJson.put(json);
		}
		return yearJson.toString();
	}
}
