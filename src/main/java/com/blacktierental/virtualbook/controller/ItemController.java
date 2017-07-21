package com.blacktierental.virtualbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blacktierental.virtualbook.model.Attachment;
import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.service.AttachmentService;
import com.blacktierental.virtualbook.service.ItemService;


@Controller
@SessionAttributes("types")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	AttachmentService attachmentService;
	
	@Autowired
    MessageSource messageSource;
	
	@RequestMapping(value={"/Item","itemList"},method=RequestMethod.GET)
	public String homePage(ModelMap model){
		List<Item> items = itemService.findAllItems();
		List<Attachment> attachment = attachmentService.findAllItemAttachment();
		model.addAttribute("items",items);
		model.addAttribute("attachments",attachment);
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

		if (result.hasErrors() || (item==null || item.getDescription()==null)) {
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

		model.addAttribute("success", "ITEM <strong>" + item.getDescription()+ "</strong> REGISTERED SUCCESSFULLY");
		// return "success";
		return "redirect:/itemList";
	}
	
	/**
	 * This method will delete an item by it's description value.
	 */
	@RequestMapping(value = { "/delete-item-{description}" }, method = RequestMethod.GET)
	public String deleteItem(@PathVariable String description, ModelMap model) {
		itemService.deleteItemByDescription(description);
		model.addAttribute("success", "ITEM <strong>" + description + "</strong> DELETED SUCCESSFULLY");
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
 
        model.addAttribute("success", "ITEM <strong>" + item.getDescription() + "</strong> UPDATED SUCCESSFULLY");
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
	
	@ModelAttribute("attachmentsList")
	public List<Attachment> initializeAttachments() {
		List<Attachment> list = attachmentService.findAllItemAttachment();
		if(list != null){
			return list;
		}else{
			return new ArrayList<Attachment>();
		}
	}
}
