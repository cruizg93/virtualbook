package com.blacktierental.virtualbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.blacktierental.virtualbook.model.Attachment;
import com.blacktierental.virtualbook.service.AttachmentService;

@Controller
public class AttachmentController {

	@Autowired
	AttachmentService attachmentService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/newattachment"},method=RequestMethod.GET)
	public String newAttachment(ModelMap model){
		Attachment attachment = new Attachment();
		model.addAttribute("attachment",attachment);
		model.addAttribute("edit",false);
		model.addAttribute("loggedinuser",getPrincipal());
		return "attachmentRegistration";
		
	}
	
	@RequestMapping(value = {"/newattachment"},method=RequestMethod.POST)
	public String saveAttachment(@Valid Attachment attachment, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			return "attachmentRegistration";
		}
		
		attachmentService.save(attachment);
		model.addAttribute("success","ATTACHMENT <strong>"+attachment.getDescription()+"</strong> REGISTERED SUCCESSFULLY");
		return "redirect:/itemList";
	}
	
	@RequestMapping(value = {"/edit-attachment-{id}"}, method = RequestMethod.GET)
	public String editAttachment(@PathVariable int id, ModelMap model){
		Attachment attachment = attachmentService.findById(id);
		model.addAttribute("attachment",attachment);
		model.addAttribute("edit",true);
		model.addAttribute("loggedinuser",getPrincipal());
		return "attachmentRegistration";
	}
	
	@RequestMapping(value = {"/edit-attachment-{id}"}, method = RequestMethod.POST)
	public String updateAttachment(@Valid Attachment attachment, BindingResult result,
			ModelMap model, @PathVariable int id){
		if(result.hasErrors()){
			model.addAttribute("edit",true);
			return "attachmentRegistration";
		}
		attachmentService.updateAttachment(attachment);
		model.addAttribute("success","ATTACHMENT <strong>"+attachment.getDescription()+"</strong> UPDATED SUCCESSFULLY");
		model.addAttribute("loggedinuser",getPrincipal());
		return "redirect:/itemList";
	}
	
	@RequestMapping(value={"/delete-attachment-{id}"}, method = RequestMethod.GET)
	public String deleteAttachment(@PathVariable int id, ModelMap model){
		attachmentService.deleteById(id);
		model.addAttribute("success", "ATTACHMENT DELETED SUCCESSFULLY");
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
}
