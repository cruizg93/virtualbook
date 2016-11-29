package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.model.Client;

public interface ClientDao {

	Client findById(int id);
	Client findByName(String name);
	Client findByNameAndCompany(String name,String companyName);
	void save(Client client);
	void deleteByNameAndCompany(String name, String companyName);
	List<Client> findAllClients();
}
