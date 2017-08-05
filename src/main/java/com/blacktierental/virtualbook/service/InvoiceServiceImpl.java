package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.ClientDao;
import com.blacktierental.virtualbook.dao.InvoiceDao;
import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Invoice;

@Service("invoiceService")
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao dao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EventService eventService;
	
	@Override
	public Invoice findById(int id) throws ObjectNotFoundException {
		return dao.findById(id);
	}

	@Override
	public void save(Invoice invoice) {
		String nextInvoiceNumber = "";
		try {
			Event e = invoice.getEvents().iterator().next();
			if(e != null){
				String text = e.getClient().getInvoiceNumber().split("[0-9]")[0];
				String[] array = e.getClient().getInvoiceNumber().split("[A-Z]");
				int number = Integer.parseInt(array[array.length-1]);
				nextInvoiceNumber = text+""+(++number);
				invoice.setInvoiceNumber(nextInvoiceNumber);
			
				dao.save(invoice);
				Client client = clientService.findById(e.getClient().getId());
				client.setInvoiceNumber(nextInvoiceNumber);
				clientService.updateClient(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int id) throws ObjectNotFoundException {
		dao.deleteById(id);
	}

	@Override
	public List<Invoice> findAllInvoices() {
		return dao.findAllInvoices();
	}

	@Override
	public List<Invoice> findByClient(Invoice invoice) {
		return dao.findByClient(invoice);
	}

	@Override
	public List<Invoice> findByEvent(Invoice invoice) {
		return dao.findByEvent(invoice);
	}

	@Override
	public void updateInvoice(Invoice invoice) throws ObjectNotFoundException {
		Invoice entity = dao.findById(invoice.getId());
		if(entity!=null){
			entity.setDueDate(invoice.getDueDate());
			entity.setEvents(invoice.getEvents());
		}
	}

	@Override
	public void collectInvoice(int id, String comment) throws ObjectNotFoundException {
		Invoice invoice = dao.findById(id);
		if(invoice!=null){
			invoice.setComment(comment);
			for(Event e: invoice.getEvents()){
				String currentComment = e.getComments();
				e.setAdvance(e.getTotal());
				e.setComments(currentComment+" \n COLLECTED BY INVOICE #"+invoice.getInvoiceNumber()+" \n"+comment);
				//eventService.updateEvent(e);
			}
		}
	}
}
