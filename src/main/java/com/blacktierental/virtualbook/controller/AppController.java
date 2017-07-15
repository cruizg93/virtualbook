package com.blacktierental.virtualbook.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.blacktierental.virtualbook.model.UserProfile;
import com.blacktierental.virtualbook.service.UserProfileService;
import com.blacktierental.virtualbook.service.UserService;
 
@Controller
@SessionAttributes("roles")
public class AppController {
 
    @Autowired
    UserService userService;
     
    @Autowired
    UserProfileService userProfileService;
     
    @Autowired
    MessageSource messageSource;

    @Autowired 
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    
    @RequestMapping(value ={"/"},method= RequestMethod.GET)
    public String homePage(ModelMap model){
    	if (isCurrentAuthenticationAnonymous()){
 		   return "login";
 	   }else{
 		   return "welcome";
 	   }
    }
    
    @RequestMapping(value={"/admin"},method=RequestMethod.GET)
    public String adminPage(ModelMap model){
    	model.addAttribute("user",getPrincipal());
    	return "admin";
    }
    
    @RequestMapping(value={"/db"},method=RequestMethod.GET)
    public String dbaPAge(ModelMap model){
    	model.addAttribute("user",getPrincipal());
    	return "dba";
    }
    
   @RequestMapping(value={"/Access_Denied"},method=RequestMethod.GET)
   public String accessDeneid(ModelMap model){
	   model.addAttribute("loggedinuser",getPrincipal());
	   return "accessDenied";
   }
 
   /**
    * This method handle login GET requests.
    * If users is already logged-in and tries to go to login page again, will be redirected to list
    */
   @RequestMapping(value={"/login"},method=RequestMethod.GET)
   public String loginPage(){
	   if (isCurrentAuthenticationAnonymous()){
		   return "login";
	   }else{
		   return "redirect:/list";
	   }
   }
   
   /**
    * This method handles logout requests.
    * Toggle the handlers if you want to deactivate RememberMe functionality 
    * @param request
    * @param response
    * @return
    */
   @RequestMapping(value="/logout",method=RequestMethod.GET)
   public String logout(HttpServletRequest request, HttpServletResponse response){
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   if (auth != null){
		   //new SecurityContextLogoutHandler().logout(request,response,auth);
		   persistentTokenBasedRememberMeServices.logout(request, response, auth);
		   SecurityContextHolder.getContext().setAuthentication(null);
	   }
	   return "redirect:/";
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
    * This method returns true if users is already authenticated [logged-in], else false.   
    * @return
    */
   private boolean isCurrentAuthenticationAnonymous(){
	   final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   return authenticationTrustResolver.isAnonymous(authentication);
   }
 
    
    @RequestMapping(value = { "/settings" }, method = RequestMethod.GET)
    public String settings(ModelMap model){
 	   model.addAttribute("loggedinuser",getPrincipal());
 	   return "settings";
    }
    
    @RequestMapping(value = { "/events" }, method = RequestMethod.GET)
    public String events(ModelMap model){
 	   model.addAttribute("loggedinuser",getPrincipal());
 	   return "eventlist";
    }
    
    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
    
}