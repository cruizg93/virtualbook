package com.blacktierental.virtualbook.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.Client;


@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao{

	static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

	@Override
	public Client findById(int id) {
		Client client = getByKey(id);
		return client;
	}

	@Override
	public Client findByName(String name) {
		System.out.println("Clien Name"+name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name",name));
		Client client = (Client)crit.uniqueResult();
		return client;
	}

	@Override
	public Client findByNameAndCompany(String name, String companyName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("companyName", companyName));
		Client client = (Client)criteria.uniqueResult();
		return client;
	}
	
	@Override
	public void save(Client client) {
		persist(client);
	}

	@Override
	public void deleteByNameAndCompany(String name, String companyName){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("companyName", companyName));
		Client client = (Client)criteria.uniqueResult();
		delete(client);
	}

	@Override
	public List<Client> findAllClients() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<Client> clients = (List<Client>)criteria.list();
		return clients;
	}

}