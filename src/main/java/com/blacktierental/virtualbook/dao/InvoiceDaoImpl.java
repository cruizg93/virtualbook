package com.blacktierental.virtualbook.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Invoice;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends AbstractDao<Integer, Invoice> implements InvoiceDao{

	@Override
	public Invoice findById(int id) throws ObjectNotFoundException {
		Invoice invoice = getByKey(id);
		if(invoice==null){
			throw new ObjectNotFoundException("Invoice with id: "+id+" was not found");
		}
		return invoice;
	}

	@Override
	public void save(Invoice invoice) {
		persist(invoice);
	}

	@Override
	public void deleteById(int id) throws ObjectNotFoundException {
		Invoice invoice = findById(id);
		delete(invoice);
	}

	@Override
	public List<Invoice> findAllInvoices() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Invoice>)criteria.list();
	}

	@Override
	public List<Invoice> findByClient(Invoice invoice) {
		if(invoice != null && !invoice.getEvents().isEmpty()){
			Event event = invoice.getEvents().iterator().next();
			if(event.getClient() != null){
				Query q = getSession().createQuery("SELECT i.* " 
						+"FROM tbl_invoice i, tbl_invoice_event ie, tbl_event e" 
						+"WHERE i.id = ie.invoice_id and ie.event_id = e.id AND e.client_id = ?" );
				q.setParameter(0, event.getClient().getId());
				return (List<Invoice>)q.getResultList();
			}	
		}
		return new ArrayList<Invoice>();
	}

	@Override
	public List<Invoice> findByEvent(Invoice invoice) {
		if(invoice != null && !invoice.getEvents().isEmpty()){
			Event event = invoice.getEvents().iterator().next();
			if(event.getClient() != null){
				Criteria criteria = createEntityCriteria();
				criteria.add(Restrictions.eq("client", event.getClient()));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				return (List<Invoice>)criteria.list();
			}	
		}
		return new ArrayList<Invoice>();
	}

	@Override
	public List findInvoicesNumberById(int id) throws ObjectNotFoundException{
		Query q = getSession().createQuery("SELECT i.invoiceNumber " 
				+"FROM Invoice i, InvoiceEvent ie, Event e "
				+"WHERE i.id = ie.invoice.id and ie.event.id = ? and e.state ='ACTIVE' "
				+"GROUP BY i.invoiceNumber ");
		q.setParameter(0, id);
		return q.getResultList();
	}
}
