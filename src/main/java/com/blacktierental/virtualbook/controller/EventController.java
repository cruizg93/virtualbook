package com.blacktierental.virtualbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyEditorSupport;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.datetime.DateFormatter;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format((LocalDateTime) getValue());
			}
		});
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
			}
		});
	}

	@RequestMapping(value = { "/delete-event-{id}" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int id, ModelMap model) {
		eventService.deleteById(id);
		model.addAttribute("success", "EVENT DELETED SUCCESSFULLY");
		return list(model);
	}
	
	/**
	 * This method will list existing event.
	 * 
	 * @param model
	 * @return view page name
	 */
	@RequestMapping(value = { "/eventlist" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		loadMonth(model, LocalDateTime.now().toLocalDate());
		return "eventlist";
	}

	@RequestMapping(value = { "/previousMonth" }, method = RequestMethod.GET, params = { "m" })
	public String previousMonth(@RequestParam(value = "m", required = true) int currentMonth,
			@RequestParam(value = "y", required = true) int currentYear, ModelMap model) {
		LocalDate monthToLoad = LocalDate.of(currentYear, currentMonth, 1);
		monthToLoad = monthToLoad.minusMonths(1);
		loadMonth(model, monthToLoad);
		return "eventlist";
	}

	@RequestMapping(value = { "/nextMonth" }, method = RequestMethod.GET, params = { "m" })
	public String nextMonth(@RequestParam(value = "m", required = true) int currentMonth,
			@RequestParam(value = "y", required = true) int currentYear, ModelMap model) {
		LocalDate monthToLoad = LocalDate.of(currentYear, currentMonth, 1);
		monthToLoad = monthToLoad.plusMonths(1);
		loadMonth(model, monthToLoad);
		return "eventlist";
	}

	/*
	 * You receive the month that you want to load
	 */
	public void loadMonth(ModelMap model, LocalDate monthToLoad) {
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
			List<Event> list = new ArrayList<Event>();
			if (i > 0 || i <= numDays) {
				for (Event event : events) {
					if ((i + 1) == event.getDateAndHour().getDayOfMonth()) {
						list.add(event);
					}
				}
			}
			eventList.add(list);
		}

		model.addAttribute("emptySpotsAtBegin", emptySpotsAtBegin);
		model.addAttribute("emptySpotsAtEnd", emptySpotsAtEnd);
		model.addAttribute("events", eventList);
		model.addAttribute("month", initial.getMonth().getValue());
		model.addAttribute("year", initial.getYear());
		model.addAttribute("count", events.size());
	}

	@RequestMapping(value = { "/newEvent" }, method = RequestMethod.GET)
	public String newEvent(ModelMap model) {
		Event event = new Event();
		event.setItems(eventItemService.onePerItem());
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
	public String saveEvent(@Valid Event event, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			model.addAttribute("event", event);
			model.addAttribute("edit", true);
			return "eventRegistration";
		}
		eventService.save(event);
		// TODO: Validate unique event by client & location & item
		model.addAttribute("success", "Event " + event.getEventName() + " registered successfully");
		return "redirect:/eventlist";
	}

	/**
	 * This method will provide the medium to update an existing location.
	 */
	@RequestMapping(value = { "/edit-event-{id}" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable String id, ModelMap model) {
		Event eventObj = eventService.findById(id != null ? Integer.parseInt(id) : 0);
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
	public String updateEvent(@Valid Event eventObj, BindingResult result, ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			// model.addAttribute("event", eventObj);
			return "eventRegistration";
		}
		if (!eventService.isEventUnique(eventObj)) {
			FieldError clientUniqueError = new FieldError("location", "location", messageSource
					.getMessage("non.unique.location", new String[] { eventObj.getEventName() }, Locale.getDefault()));
			result.addError(clientUniqueError);
			return "eventRegistration";
		}
		eventService.updateEvent(eventObj);

		model.addAttribute("success", "Event " + eventObj.getEventName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/eventlist";
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
