package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Invoice;

public interface InvoiceDao {

	Invoice findById(int id) throws ObjectNotFoundException;
	void save(Invoice invoice);
	void deleteById(int id) throws ObjectNotFoundException;
	List<Invoice> findAllInvoices();
	List<Invoice> findByClient(Invoice invoice);
	List<Invoice> findByEvent(Invoice invoice);
	List findInvoicesNumberById(int id) throws ObjectNotFoundException;
}
