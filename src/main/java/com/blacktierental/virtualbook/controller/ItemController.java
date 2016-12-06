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

import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.model.ItemType;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.model.User;
import com.blacktierental.virtualbook.model.UserProfile;
import com.blacktierental.virtualbook.service.ItemService;
import com.blacktierental.virtualbook.service.ItemTypeService;


@Controller
@SessionAttributes("types")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
    MessageSource messageSource;
	
	@Autowired
	ItemTypeService itemTypeService;
	
	@RequestMapping(value={"/Item","itemList"},method=RequestMethod.GET)
	public String homePage(ModelMap model){
		List<Item> items = itemService.findAllItems();
		model.addAttribute("items",items);
		model.addAttribute("loggedinuser",getPrincipal());
		return "itemlist";
	}
	
	/**
	 * This method will provide the medium to add a new user
	 * 
	 * @param model
	 * @return return view page name
	 */
	@RequestMapping(value = { "/newitem" }, method = RequestMethod.GET)
	public String newItem(ModelMap model) {
		Item item = new Item();
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "itemRegistration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving item in database. It also validates the item input
	 */
	@RequestMapping(value = { "/newitem" }, method = RequestMethod.POST)
	public String saveItem(@Valid Item item,BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "itemRegistration";
		}

		if (!itemService.isItemDescriptionUnique(item.getId(),item.getDescription())) {
			FieldError clientUniqueError = new FieldError("item", "description",
					messageSource.getMessage("non.unique.item", new
					String[] {item.getDescription()}, Locale.getDefault()));
			result.addError(clientUniqueError);
			return "itemRegistration";
		}

		itemService.saveItem(item);

		model.addAttribute("success", "Item " + item.getDescription()+ " registered successfully");
		// return "success";
		return "redirect:/itemList";
	}
	
	/**
	 * This method will delete an item by it's description value.
	 */
	@RequestMapping(value = { "/delete-item-{description}" }, method = RequestMethod.GET)
	public String deleteItem(@PathVariable String description) {
		itemService.deleteItemByDescription(description);
		return "redirect:/itemList";
	}

	/**
	 * This method will provide the medium to update an existing item.
	 */
	@RequestMapping(value = { "/edit-item-{description}" }, method = RequestMethod.GET)
	public String editItem(@PathVariable String description, ModelMap model) {
		Item item = itemService.findByDescription(description);
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "itemRegistration";
	}
	
	/**
     * This method will be called on form submission, handling POST request for
     * updating item in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-item-{description}" }, method = RequestMethod.POST)
    public String updateUser(@Valid Item item, BindingResult result,
            ModelMap model, @PathVariable String description) {
 
        if (result.hasErrors()) {
            return "itemRegistration";
        }
 
        if(!itemService.isItemDescriptionUnique(item.getId(), item.getDescription())){
            FieldError descriptionError =new FieldError("item","description",messageSource.getMessage("non.unique.item", new 
 
            String[]{item.getDescription()}, Locale.getDefault()));
            result.addError(descriptionError);
            return "itemRegistration";
        }
 
        itemService.updateItem(item);
 
        model.addAttribute("success", "Item" + item.getDescription() + " updated successfully");
        model.addAttribute("loggedinuser",getPrincipal());
        return "redirect:/itemList";
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
	
	/**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("types")
    public List<ItemType> initializeItemTypes() {
        return itemTypeService.findAll();
    }
}
