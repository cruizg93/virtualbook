package com.blacktierental.virtualbook.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.service.ClientService;

@Component
public class ClientConverter implements Converter<Object, Client> {

	static final Logger logger = LoggerFactory.getLogger(ClientConverter.class);
	
	@Autowired
	ClientService clientService;
	
	/**
     * Gets Client by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	public Client convert(Object element){
		if(element instanceof Client){
			return (Client) element;
		}else{
			
			try {
				Integer id = Integer.parseInt((String)element);
				Client client = clientService.findById(id);
				logger.info("Client:",client);
				return client;
			} catch (ObjectNotFoundException e) {
				return null;
			}
		}
	}
}
