package com.blacktierental.virtualbook.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.service.ClientService;
 
@Controller
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value ={"/Client","/clientlist"},method= RequestMethod.GET)
    public String homePage(ModelMap model){
 		List<Client> clients = clientService.findAllClients();
 		model.addAttribute("clients",clients);
 		model.addAttribute("loggedinuser",getPrincipal());
		return "clientlist";
    }
	
	/**
	 * This method will provide the medium to add a new client
	 * 
	 * @param model
	 * @return return view page name
	 */
	@RequestMapping(value = { "/newclient" }, method = RequestMethod.GET)
	public String newClient(ModelMap model) {
		Client client= new Client();
		model.addAttribute("client", client);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "clientRegistration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving client in database. It also validates the client input
	 */
	@RequestMapping(value = { "/newclient" }, method = RequestMethod.POST)
	public String saveClient(@Valid Client client, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "clientRegistration";
		}

		if (!clientService.isNameAndCompanyUnique(client.getId(), client.getName(), client.getCompanyName())) {
			FieldError clientUniqueError = new FieldError("client", "name",
					messageSource.getMessage("non.unique.client.nameAndCompany", new
					String[] { client.getName(),client.getCompanyName()}, Locale.getDefault()));
			result.addError(clientUniqueError);
			return "clientRegistration";
		}

		clientService.saveClient(client);

		model.addAttribute("success", "CLIENT <strong>" + client.getName() + "<strong> REGISTERED SUCCESSFULLY");
		// return "success";
		return "redirect:/clientlist";
	}
	
	/**
	 * This method will delete an client by it's name and company.
	 */
	@RequestMapping(value = { "/delete-client-{id}" }, method = RequestMethod.GET)
	public String deleteClient(@PathVariable int id, ModelMap model) {
		clientService.deleteById(id);
		model.addAttribute("success", "CLIENT DELETED SUCCESSFULLY");
		return "redirect:/clientlist";
	}

	/**
	 * This method will provide the medium to update an existing client.
	 */
	@RequestMapping(value = { "/edit-client-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable int id, ModelMap model) {
		Client client = clientService.findById(id);
		model.addAttribute("client", client);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "clientRegistration";
	}
	
	/**
     * This method will be called on form submission, handling POST request for
     * updating client in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-client-{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid Client client, BindingResult result,
            ModelMap model, @PathVariable int id) {
 
        if (result.hasErrors()) {
            return "clientRegistration";
        }
 
        clientService.updateClient(client);
 
        model.addAttribute("success", "CLIENT <strong>" + client.getName() + "</strong> UPDATED SUCCESSFULLY");
        model.addAttribute("loggedinuser",getPrincipal());
        return "redirect:/clientlist";
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
