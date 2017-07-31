package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;

public interface ClientService {
	Client findById(int id) throws ObjectNotFoundException;
	Client findByName(String name);
	Client findByNameAndCompany(String name,String companyName);
	
	void saveClient(Client  client);
	void updateClient(Client client) throws ObjectNotFoundException;
	void deleteByNameAndCompany(String name, String companyName);
	void deleteById(int id) throws ObjectNotFoundException;
	List<Client> findAllClients();
	boolean isNameAndCompanyUnique(Integer id, String name, String companyName);
}
