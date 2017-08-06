package com.blacktierental.virtualbook.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.beans.PropertyEditorSupport;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.service.ClientService;
import com.blacktierental.virtualbook.service.EventItemService;
import com.blacktierental.virtualbook.service.EventService;
import com.blacktierental.virtualbook.service.ExporterServiceImpl;
import com.blacktierental.virtualbook.service.ItemService;
import com.blacktierental.virtualbook.service.LocationService;

@Controller
public class EventController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	EventService eventService;

	@Autowired
	EventItemService eventItemService;
	
	@Autowired
	ItemService itemService;
	@Autowired
	ClientService clientService;
	@Autowired
	LocationService locationService;

	//This variable is use to catch when the localDateTime fields are empty or null
	//Check only when newEvent[POST] is called and assign null
	private final String DATE_TO_NULL_FORMAT = "1999-01-01 00:00";
	private final String DATE_TO_NULL = "1999-01-01T00:00";
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if(text==null || text.trim().equals("") ){
					setValue(LocalDateTime.parse(DATE_TO_NULL_FORMAT, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
				}else{
					setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
				}
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				if(getValue()==null ){
					return "";
				}
				return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format((LocalDateTime) getValue());
			}
		});
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if(text==null || text.isEmpty()){
					setValue("");
				}
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				if(getValue()==null){
					return "";
				}
				return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
			}
		});
	}

	@RequestMapping(value = { "/delete-event-{id}" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int id, ModelMap model, HttpServletRequest request) {
		eventService.deleteById(id);
		model.addAttribute("success", "EVENT DELETED SUCCESSFULLY");
		String redirect = request.getSession().getAttribute("pervious_page").toString();
		if(redirect!=null){
			redirect = redirect.split("/")[redirect.split("/").length-1];
		}
		return "redirect:/"+redirect;
	}
	
	/**
	 * This method will list existing event.
	 * 
	 * @param model
	 * @return view page name
	 */
	@RequestMapping(value = { "/eventlist" }, method = RequestMethod.GET)
	public String list(ModelMap model, HttpServletRequest request) {
		loadMonth(model, LocalDateTime.now().toLocalDate());
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl);
		return "eventlist";
	}

	/**
	 * incomplete event is those event with dateAndHour equals to null
	 * @param model
	 */
	private void loadIncompleteEvents(ModelMap model){
		List<Event> events = eventService.findIncompleteEvents();
		model.addAttribute("incompleteEvents",events);
	}
	
	@RequestMapping(value = { "/previousMonth" }, method = RequestMethod.GET, params = { "m" })
	public String previousMonth(@RequestParam(value = "m", required = true) int currentMonth,
			@RequestParam(value = "y", required = true) int currentYear, ModelMap model, HttpServletRequest request) {
		LocalDate monthToLoad = LocalDate.of(currentYear, currentMonth, 1);
		monthToLoad = monthToLoad.minusMonths(1);
		loadMonth(model, monthToLoad);
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl+"?m="+currentMonth+"&y="+currentYear);
		return "eventlist";
	}

	@RequestMapping(value = { "/nextMonth" }, method = RequestMethod.GET, params = { "m" })
	public String nextMonth(@RequestParam(value = "m", required = true) int currentMonth,
			@RequestParam(value = "y", required = true) int currentYear, ModelMap model,HttpServletRequest request) {
		LocalDate monthToLoad = LocalDate.of(currentYear, currentMonth, 1);
		monthToLoad = monthToLoad.plusMonths(1);
		loadMonth(model, monthToLoad);
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl+"?m="+currentMonth+"&y="+currentYear);
		return "eventlist";
	}
	
	@RequestMapping(value = { "/month" }, method = RequestMethod.GET, params = { "m" })
	public String month(@RequestParam(value = "m", required = true) int currentMonth,
			@RequestParam(value = "y", required = true) int currentYear, ModelMap model,HttpServletRequest request) {
		LocalDate monthToLoad = LocalDate.of(currentYear, currentMonth, 1);
		loadMonth(model, monthToLoad);
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl+"?m="+currentMonth+"&y="+currentYear);
		return "eventlist";
	}

	@RequestMapping(value = { "/calendar" }, method = RequestMethod.GET)
	public String calendarList(ModelMap model, HttpServletRequest request) {
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl);
		loadYear(model, LocalDate.now());
		loadIncompleteEvents(model);
		return "calendar";
	}
	
	@RequestMapping(value = { "/previousYear" }, method = RequestMethod.GET, params = { "y" })
	public String previousYear(@RequestParam(value = "y", required = true) int currentYear, ModelMap model, HttpServletRequest request) {
		LocalDate yearToLoad = LocalDate.of(currentYear,1, 1);
		yearToLoad = yearToLoad.minusYears(1);
		loadYear(model, yearToLoad );
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl+"?y="+currentYear);
		return "calendar";
	}

	@RequestMapping(value = { "/nextYear" }, method = RequestMethod.GET, params = { "y" })
	public String nextYear(@RequestParam(value = "y", required = true) int currentYear, ModelMap model,HttpServletRequest request) {
		LocalDate yearToLoad = LocalDate.of(currentYear,1, 1);
		yearToLoad= yearToLoad.plusYears(1);
		loadYear(model, yearToLoad);
		loadIncompleteEvents(model);
		String requestUrl=request.getRequestURL().toString();
		request.getSession().setAttribute("pervious_page",requestUrl+"?y="+currentYear);
		return "calendar";
	}
	
	private void loadYear(ModelMap model, LocalDate yearToLoad){
		LocalDate initial = LocalDate.of(yearToLoad.getYear(), Month.JANUARY, Month.JANUARY.minLength());
		LocalDate last = LocalDate.of(yearToLoad.getYear(), Month.DECEMBER, Month.DECEMBER.maxLength());
		List<Event> events = eventService.findAllEventsByDateRange(initial, last);
		List<List<Event>> eventList = new ArrayList<List<Event>>();
		for(int i=1;i<=12;i++){
			eventList.add(new ArrayList<Event>());
		}
		for(Event event: events){
			int month = event.getDateAndHour().getMonth().getValue();
			eventList.get(month).add(event);
		}
		model.addAttribute("eventsList", events);
		model.addAttribute("events", eventList);
		model.addAttribute("year", initial.getYear());
		model.addAttribute("count", events.size());
	}
	
	
	private void loadMonth(ModelMap model, LocalDate monthToLoad) {
		int emptySpotsAtBegin = 0;
		int emptySpotsAtEnd = 0;
		int numDays = monthToLoad.getMonth().maxLength();
		if (monthToLoad.getMonth() == Month.FEBRUARY && !monthToLoad.isLeapYear()) {
			numDays--;
		}

		LocalDate initial = LocalDate.of(monthToLoad.getYear(), monthToLoad.getMonthValue(), 1);
		LocalDate last = LocalDate.of(monthToLoad.getYear(), monthToLoad.getMonthValue(), numDays);

		List<Event> events = eventService.findAllEventsByDateRange(initial, last);
		List<List<Event>> eventList = new ArrayList<List<Event>>();

		if (initial.getDayOfWeek() != DayOfWeek.MONDAY) {
			emptySpotsAtBegin -= (DayOfWeek.MONDAY.getValue() - initial.getDayOfWeek().getValue());
		}
		if (last.getDayOfWeek() != DayOfWeek.SUNDAY) {
			emptySpotsAtEnd = (DayOfWeek.SUNDAY.getValue() - last.getDayOfWeek().getValue()) - 1;
		}
		
		for (int i = (emptySpotsAtBegin * -1); i <= (numDays + emptySpotsAtEnd); i++) {
			eventList.add(new ArrayList<Event>());
		}
		for(Event event: events){
			int day = event.getDateAndHour().getDayOfMonth() + emptySpotsAtBegin;
			eventList.get(day).add(event);
		}
		model.addAttribute("emptySpotsAtBegin", emptySpotsAtBegin);
		model.addAttribute("emptySpotsAtEnd", emptySpotsAtEnd);
		model.addAttribute("eventsList", events);
		model.addAttribute("events", eventList);
		model.addAttribute("month", initial.getMonth().getValue());
		model.addAttribute("year", initial.getYear());
		model.addAttribute("count", events.size());
	}

	@RequestMapping(value = { "/newEvent" }, method = RequestMethod.GET)
	public String newEvent(ModelMap model, HttpServletRequest request) {
		Event event = new Event();
		event.setItems(eventItemService.onePerItem());
		String redirect = request.getSession().getAttribute("pervious_page").toString();
		if(redirect!=null){
			redirect = redirect.split("/")[redirect.split("/").length-1];
		}
		model.addAttribute("redirect", "/"+redirect);
		model.addAttribute("event", event);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "eventRegistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving event in database. It also validates the user input
	 */

	@RequestMapping(value = { "/newEvent" }, method = RequestMethod.POST)
	public String saveEvent(@Valid Event event, BindingResult result, ModelMap model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "eventRegistration";
		}
		try {
			if (!eventService.isEventUnique(event)) {
				FieldError clientUniqueError = new FieldError("event", "dateAndHour",
						messageSource.getMessage("non.unique.event.clientNLocation", new
						String[] { event.getClient().getName(),event.getLocation().getBuildingName(),event.getDateAndHour().toString()}, Locale.getDefault()));
				result.addError(clientUniqueError);
				model.addAttribute("event", event);
				return "eventRegistration";
			}
		} catch (NoSuchMessageException | ObjectNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "exception";
		}
		String hora = event.getDateAndHour().toString();
		if(event.getDateAndHour()!=null && event.getDateAndHour().toString().equals(DATE_TO_NULL)){
			event.setDateAndHour(null);
		}
		if(event.getDropOffTime()!=null && event.getDropOffTime().toString().equals(DATE_TO_NULL)){
			event.setDropOffTime(null);
		}
		if(event.getPickUpTime()!=null && event.getPickUpTime().toString().equals(DATE_TO_NULL)){
			event.setPickUpTime(null);
		}
		eventService.save(event);
		model.addAttribute("success", "Event " + event.getEventName() + " registered successfully");
		//return "redirect:/eventlist";
		String redirect = request.getSession().getAttribute("pervious_page").toString();
		if(redirect!=null){
			redirect = redirect.split("/")[redirect.split("/").length-1];
		}
		return "redirect:/"+redirect;
	}

	/**
	 * This method will provide the medium to update an existing location.
	 */
	@RequestMapping(value = { "/edit-event-{id}" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable String id, ModelMap model, HttpServletRequest request) {
		Event eventObj = null;
		try {
			eventObj = eventService.findById(id != null ? Integer.parseInt(id) : 0);
		} catch (NumberFormatException | ObjectNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "exception";
		}
		String redirect = request.getSession().getAttribute("pervious_page").toString();
		if(redirect!=null){
			redirect = redirect.split("/")[redirect.split("/").length-1];
		}
		model.addAttribute("redirect", "/"+redirect);
		model.addAttribute("event", eventObj);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "eventRegistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating client in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-event-{id}" }, method = RequestMethod.POST)
	public String updateEvent(@Valid Event eventObj, BindingResult result, ModelMap model, @PathVariable String id,HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("event", eventObj);
			return "eventRegistration";
		}
		try {
			if (!eventService.isEventUnique(eventObj)) {
				FieldError clientUniqueError = new FieldError("location", "location", messageSource
						.getMessage("non.unique.location", new String[] { eventObj.getEventName() }, Locale.getDefault()));
				result.addError(clientUniqueError);
				model.addAttribute("event", eventObj);
				return "eventRegistration";
			}
			if(eventObj.getDateAndHour()!=null && eventObj.getDateAndHour().toString().equals(DATE_TO_NULL)){
				eventObj.setDateAndHour(null);
			}
			if(eventObj.getDropOffTime()!=null && eventObj.getDropOffTime().toString().equals(DATE_TO_NULL)){
				eventObj.setDropOffTime(null);
			}
			if(eventObj.getPickUpTime()!=null && eventObj.getPickUpTime().toString().equals(DATE_TO_NULL)){
				eventObj.setPickUpTime(null);
			}
			eventService.updateEvent(eventObj);

		} catch (NoSuchMessageException | ObjectNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "exception";
		}
		model.addAttribute("success", "Event " + eventObj.getEventName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "redirect:/eventlist";
		String redirect = request.getSession().getAttribute("pervious_page").toString();
		if(redirect!=null){
			redirect = redirect.split("/")[redirect.split("/").length-1];
		}
		return "redirect:/"+redirect;
	}

	private String getPrincipal() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	@ModelAttribute("existingMainItems")
	public List<Item> initializeItems() {
		return itemService.findAllItems();
	}

	@RequestMapping(value="/contract-{id}")
	public void download(@PathVariable int id, HttpServletResponse response) {
		eventService.downloadContract(ExporterServiceImpl.EXTENSION_TYPE_PDF, "", response, id);
	}
	
	@ModelAttribute("clients")
	public List<Client> initializeClients() {
		return clientService.findAllClients();
	}

	@ModelAttribute("locations")
	public List<Location> initializeLocations() {
		return locationService.findAllLocations();
	}

}
