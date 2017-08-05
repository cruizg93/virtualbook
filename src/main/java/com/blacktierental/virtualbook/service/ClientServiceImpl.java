package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.ClientDao;
import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.State;


@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private  ClientDao dao;
	
	@Autowired
	private EventDao eventDao;
	
	@Override
	public Client findById(int id) throws ObjectNotFoundException {
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
	public void updateClient(Client client) throws ObjectNotFoundException {
		Client entity  = dao.findById(client.getId());
		if(entity!= null){
			entity.setCompanyName(client.getCompanyName());
			entity.setEmail(client.getEmail());
			entity.setName(client.getName());
			entity.setPhoneNumber(client.getPhoneNumber());
			entity.setInvoiceNumber(client.getInvoiceNumber());
		}
	}

	@Override
	public void deleteByNameAndCompany(String name, String companyName) {
		dao.deleteByNameAndCompany(name, companyName);
	}
	
	@Override
	public void deleteById(int id) throws ObjectNotFoundException {
		Client client = dao.findById(id);
		List<Event> events = eventDao.findByClient(client);
		if(events!=null && !events.isEmpty()){
			client.setState(State.DELETED.toString());
			dao.save(client);
		}else{
			dao.deleteById(id,client);
		}
		
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
