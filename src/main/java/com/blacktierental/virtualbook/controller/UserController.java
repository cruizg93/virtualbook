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

import com.blacktierental.virtualbook.model.User;
import com.blacktierental.virtualbook.service.UserProfileService;
import com.blacktierental.virtualbook.service.UserService;;

@Controller
@SessionAttributes("roles")
public class UserController {

	@Autowired
    UserService userService;
     
    @Autowired
    UserProfileService userProfileService;
     
    @Autowired
    MessageSource messageSource;

	
	/**
	 * This method will list all existing users.
	 * 
	 * @param model
	 * @return view page name
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userlist";
	}

	/**
	 * This method will provide the medium to add a new user
	 * 
	 * @param model
	 * @return return view page name
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [username] should be
		 * implementing custom @Unique annotation and applying it on field
		 * [username] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the
		 * 
		 * validation framework as well while still using internationalized
		 * messages.
		 * 
		 */
		if (!userService.isUserUsernameUnique(user.getId(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username",
					messageSource.getMessage("non.unique.username", new

					String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success", "USER <strong>" + user.getName() + "-"+user.getUsername()+"</strong> REGISTERED SUCCESSFULLY");
		// return "success";
		return "redirect:/list";
	}

	/**
	 * This method will delete an user by it's name value.
	 */
	@RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username, ModelMap model) {
		userService.deleteUserByUsername(username);
		model.addAttribute("success", "USER <strong>" + username + "</strong> DELETE SUCCESSFULLY");
		return "redirect:/list";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String username) {
 
        if (result.hasErrors()) {
            return "registration";
        }
 
        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING USERNAME in UI which is a unique key to a User.
        if(!userService.isUsernameUnique(user.getId(), user.getUsername())){
            FieldError ssoError =new FieldError("user","username",messageSource.getMessage("non.unique.ssoId", new 
 
String[]{user.getUsername()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/
 
        userService.updateUser(user);
 
        model.addAttribute("success", "USER <strong>" + user.getName().toUpperCase() + "</strong> UPDATED SUCCESSFULLY");
        model.addAttribute("loggedinuser",getPrincipal());
        return "redirect:/list";
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
