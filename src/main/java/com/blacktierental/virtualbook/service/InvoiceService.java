package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Invoice;

public interface InvoiceService {

	Invoice findById(int id) throws ObjectNotFoundException;
	void save(Invoice invoice);
	void deleteById(int id) throws ObjectNotFoundException;
	void updateInvoice(Invoice invoice)throws ObjectNotFoundException;
	List<Invoice> findAllInvoices();
	List<Invoice> findByClient(Invoice invoice);
	List<Invoice> findByEvent(Invoice invoice);
	void collectInvoice(int id, String comment)throws ObjectNotFoundException;
}

