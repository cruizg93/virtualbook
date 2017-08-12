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
			}catch(NumberFormatException ex){
				Client client = clientService.findByName((String)element);
				if(client == null){
					client = new Client();
					client.setCompanyName((String)element);
					client.setName((String)element);
					client.setPhoneNumber("(000) 000-0000");
					client.setInvoiceNumber("INV000");
					clientService.saveClient(client);
				}
				return client;
			}catch (ObjectNotFoundException e) {
				return null;
			}
		}
	}
}
