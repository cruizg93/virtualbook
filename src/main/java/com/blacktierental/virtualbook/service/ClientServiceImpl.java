package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.ClientDao;
import com.blacktierental.virtualbook.model.Client;


@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private  ClientDao dao;
	
	@Override
	public Client findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Client findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Client findByNameAndCompany(String name, String companyName) {
		return dao.findByNameAndCompany(name, companyName);
	}
	
	@Override
	public void saveClient(Client client) {
		dao.save(client);
	}

	@Override
	public void updateClient(Client client) {
		Client entity  = dao.findById(client.getId());
		if(entity!= null){
			entity.setCompanyName(client.getCompanyName());
			entity.setEmail(client.getEmail());
			entity.setName(client.getName());
			entity.setPhoneNumber(client.getPhoneNumber());
		}
	}

	@Override
	public void deleteByNameAndCompany(String name, String companyName) {
		dao.deleteByNameAndCompany(name, companyName);
	}
	
	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Client> findAllClients() {
		return dao.findAllClients();
	}

	@Override
	public boolean isNameAndCompanyUnique(Integer id, String name, String companyName) {
		Client client = findByNameAndCompany(name, companyName);
		return (client == null || ((id != null) && (client.getId()==id)));
	}

}