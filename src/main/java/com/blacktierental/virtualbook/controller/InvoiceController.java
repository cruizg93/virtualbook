package com.blacktierental.virtualbook.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Invoice;
import com.blacktierental.virtualbook.model.Location;
import com.blacktierental.virtualbook.service.ClientService;
import com.blacktierental.virtualbook.service.EventService;
import com.blacktierental.virtualbook.service.InvoiceService;

@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	ClientService clientService;

	@Autowired
	EventService eventService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
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
	

    @RequestMapping(value={"/invoicelist"},method = RequestMethod.GET)
    public String invoiceList(ModelMap model){
    	List<Invoice> invoices= invoiceService.findAllInvoices();
    	model.addAttribute("invoices",invoices);
    	return "invoicelist";
    }
    
    @RequestMapping(value={"/newInvoice"},method = RequestMethod.GET)
    public String invoice(ModelMap model){
    	List<Client> clients = clientService.findAllClients();
    	Invoice invoice = new Invoice();
    	model.addAttribute("invoice",invoice);
    	model.addAttribute("clients",clients);
    	model.addAttribute("edit", false);
    	return "invoiceRegistration";
    }
    
    @RequestMapping(value = { "/newInvoice" }, method = RequestMethod.POST)
	public String saveInvoice(@Valid Invoice invoice, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
	    	model.addAttribute("invoice",invoice);
	    	model.addAttribute("edit", false);
			return "invoiceRegistration";
		}
		invoiceService.save(invoice);
		model.addAttribute("success", "Invoice <strong>" + invoice.getInvoiceNumber() + "</strong> REGISTERED SUCCESSFULLY");
		
		return "redirect:/invoicelist";
	}
    
    @RequestMapping(value = { "/edit-invoice-{id}" }, method = RequestMethod.GET)
	public String editInvoice(@PathVariable int id, ModelMap model) {
		try {
			List<Client> clients = clientService.findAllClients();
			Invoice invoice = invoiceService.findById(id);
			model.addAttribute("invoice", invoice);
			model.addAttribute("clients",clients);
			model.addAttribute("edit", true);
			return "invoiceRegistration";
		} catch (ObjectNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "exception";
		}
	}
	
	/**
     * This method will be called on form submission, handling POST request for
     * updating client in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-invoice-{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid Invoice invoice, BindingResult result,
            ModelMap model, @PathVariable int id) {
        try {
            if (result.hasErrors()) {
                return "invoiceRegistration";
            }
        	invoiceService.updateInvoice(invoice);
			model.addAttribute("success", "Invoice <strong>" + invoice.getInvoiceNumber() + "</strong> UPDATED SUCCESSFULLY");
	        return "redirect:/invoicelist";
        } catch (ObjectNotFoundException e) {
        	model.addAttribute("message",e.getMessage());
        	return "exception";
        }
    }
    
    
	@RequestMapping(value = "/nonPaidEventsByClient", method = RequestMethod.POST)
	public @ResponseBody List<Event> getNonPaidEventsByClient(@RequestBody String rclientId) {
		try {
			int clientId = Integer.parseInt(rclientId);
			Client client = clientService.findById(clientId);
			List<Event> events = eventService.findByClient(client);
			List<Event> result = new ArrayList<Event>();
			for(Event e: events){
				if(e.isPaid()){
					continue;
				}
				Event ex = new Event();
				ex.setId(e.getId());
				ex.setDateAndHour(e.getDateAndHour());
				if(e.getLocation()!=null){
					ex.setLocation(new Location(e.getLocation().getLocation(),e.getLocation().getBuildingName()));
				}else{
					ex.setLocation(new Location("",""));
				}
				result.add(ex);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/collectInvoice", method = RequestMethod.POST)
	public @ResponseBody String collectInvoice(@RequestBody String data) {
		try {
			String[] array = data.split("<");
			String comment = "";
			int id = -1;
			if(array.length==2){
				id = Integer.parseInt(array[0]);
				comment = array[1];
			}
			invoiceService.collectInvoice(id, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@RequestMapping(value = { "/delete-invoice-{id}" }, method = RequestMethod.GET)
	public String deleteInvoice(@PathVariable int id, ModelMap model) {
		try {
			invoiceService.deleteById(id);
			model.addAttribute("success", "INVOICE DELETED SUCCESSFULLY");
			return "redirect:/invoicelist";
		} catch (ObjectNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "exception";
		}
	}

	@ModelAttribute("clients")
	public List<Client> initializeClients() {
		return clientService.findAllClients();
	}
}
