package com.blacktierental.virtualbook.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.service.LocationService;
 
@Controller
public class LocationController {

	@Autowired
	LocationService locationService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value={"/Location","/locationlist"}, method=RequestMethod.GET)
	public String homePage(ModelMap model){
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		return "locationlist";
	}
	
	/**
	 * This method will provide the medium to add a new Location
	 * 
	 * @param model
	 * @return return view page name
	 */
	@RequestMapping(value = { "/newlocation" }, method = RequestMethod.GET)
	public String newLocation(ModelMap model) {
		Location location= new Location();
		model.addAttribute("location", location);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "locationRegistration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving client in database. It also validates the client input
	 */
	@RequestMapping(value = { "/newlocation" }, method = RequestMethod.POST)
	public String saveLocation(@Valid Location location, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "locationRegistration";
		}

		if (!locationService.isLocationUnique(location.getId(),location.getLocation())) {
			FieldError clientUniqueError = new FieldError("location", "location",
					messageSource.getMessage("non.unique.location", new
					String[] {location.getLocation()}, Locale.getDefault()));
			result.addError(clientUniqueError);
			return "locationRegistration";
		}

		locationService.save(location);

		model.addAttribute("success", "LOCATION <strong>" + location.getLocation() + "</strong> REGISTERED SUCCESSFULLY");
		// return "success";
		return "redirect:/locationlist";
	}
	
	/**
	 * This method will provide the medium to update an existing location.
	 */
	@RequestMapping(value = { "/edit-location-{location}" }, method = RequestMethod.GET)
	public String editLocation(@PathVariable String location, ModelMap model) {
		Location locationObj = locationService.findByLocation(location);
		model.addAttribute("location", locationObj);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "locationRegistration";
	}
	
	/**
     * This method will be called on form submission, handling POST request for
     * updating client in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-location-{location}" }, method = RequestMethod.POST)
    public String updateLocation(@Valid Location locationObj, BindingResult result,
            ModelMap model, @PathVariable String location) {
 
        if (result.hasErrors()) {
            return "locationRegistration";
        }
        if(!locationService.isLocationUnique(locationObj.getId(), locationObj.getLocation())){
        	FieldError clientUniqueError = new FieldError("location", "location",
					messageSource.getMessage("non.unique.location", new
					String[] {locationObj.getLocation()}, Locale.getDefault()));
			result.addError(clientUniqueError);
			return "locationRegistration";
		}
        
        locationService.updateLocation(locationObj);
 
        model.addAttribute("success", "LOCATION <strong>" + locationObj.getLocation() + "</strong> UPDATED SUCCESSFULLY");
        model.addAttribute("loggedinuser",getPrincipal());
        return "redirect:/locationlist";
    }
	
	/**
	 * This method will delete a location by it's location value.
	 */
	@RequestMapping(value = { "/delete-location-{id}" }, method = RequestMethod.GET)
	public String deleteLocation(@PathVariable int id, ModelMap model) {
		locationService.deleteById(id);
		model.addAttribute("success", "LOCATION DELETED SUCCESSFULLY");
		return "redirect:/locationlist";
	}
	
	private String getPrincipal(){
	   String username = null;
	   Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   if(principal instanceof UserDetails){
		   username = ((UserDetails)principal).getUsername();
	   }else{
		   username = principal.toString();
	   }
	   return username;
	}
}
