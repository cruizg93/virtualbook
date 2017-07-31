package com.blacktierental.virtualbook.controller;

import java.sql.SQLException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;

@Controller
@ControllerAdvice
public class ErrorController {
	/*
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerGenericAllException(Exception  ex ){
	     ModelAndView mv = new ModelAndView("exception");
	     mv.addObject("generic","Please show this message to your programmer");
	     mv.addObject("message", ex.getMessage()+"</br>"+ex.getStackTrace());
	     return mv;
	}*/
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(NoHandlerFoundException ex ){
	     ModelAndView mv = new ModelAndView("404");
	     mv.addObject("message", ex.getMessage());
	     return mv;
	}
	@RequestMapping(value ={"/404"},method= RequestMethod.GET)
    public String notfound(ModelMap model){
		return "404";
    }
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ModelAndView handlerObjectNotFoundException(ObjectNotFoundException ex ){
	     ModelAndView mv = new ModelAndView("exception");
	     mv.addObject("message", ex.getMessage());
	     return mv;
	}
	
	@RequestMapping(value ={"/exception"},method= RequestMethod.GET)
    public String exception(ModelMap model){
		return "exception";
    }
}

