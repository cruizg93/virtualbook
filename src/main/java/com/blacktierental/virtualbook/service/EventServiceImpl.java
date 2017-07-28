package com.blacktierental.virtualbook.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.EventDao;
import com.blacktierental.virtualbook.model.Client;
import com.blacktierental.virtualbook.model.Event;
import com.blacktierental.virtualbook.model.Location;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private ExporterService exporter;
	
	@Autowired
	private EventDao dao;
	
	@Override
	public Event findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Event> findByClient(Client client) {
		return dao.findByClient(client);
	}

	@Override
	public List<Event> findByLocation(Location location) {
		return dao.findByLocation(location);
	}

	@Override
	public List<Event> findByDate(LocalDate date) {
		return dao.findByDate(date);
	}

	@Override
	public void save(Event event) {
		dao.save(event);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Event> findAllEventsByDateRange(LocalDate initial, LocalDate end) {
		LocalDateTime i = initial.atTime(0,0,0);
		LocalDateTime e = end.atTime(23, 59, 59);
		return dao.findAllEventsByDateRange(i, e);
	}

	@Override
	public List monthlyCountByYear(int year){
		return dao.findAllByYearGroupByMonth(year);
	}
	
	@Override
	public List clientCountByYear(int year){
		return dao.findEventByYearGroupByClient(year);
	}
	
	@Override
	public List<Event> findAllUpComingEvent() {
		return dao.findAllUpComingEvent();
	}

	@Override
	public void updateEvent(Event event) {
		Event entity = dao.findById(event.getId());
		if(entity != null){
			entity.setAdvance(event.getAdvance());
			entity.setClient(event.getClient());
			entity.setComments(event.getComments());
			entity.setContactSameAsClient(event.getContactSameAsClient());
			entity.setContactPersonEmail(event.getContactPersonEmail());
			entity.setContactPersonName(event.getContactPersonName());
			entity.setContactPersonPhoneNumber(event.getContactPersonPhoneNumber());
			entity.setDateAndHour(event.getDateAndHour());
			entity.setDelivery(event.getDelivery());
			entity.setEventName(event.getEventName());
			entity.setLocation(event.getLocation());
			entity.setTaxPercentage(event.getTaxPercentage());
			entity.setItems(event.getItems());
			System.out.println("");
		}
	}
	
	

	@Override
	public boolean isEventUnique(Event event) {
		Event result = dao.findByDateNClientNLocation(event);
		if(event.equals(result)){
			return true;
		}
		return result==null?true:false;
	}
	
	@Override
	public void downloadContract(String type, String token, HttpServletResponse response, int eventId) {
		 
		try {
			Event event = findById(eventId);
			List<Event> events = new ArrayList<Event>();
			events.add(event);
			JRBeanCollectionDataSource eventsJRB = new JRBeanCollectionDataSource(events);
			JRBeanCollectionDataSource itemsJRB = new JRBeanCollectionDataSource(events.get(0).getItems());
			
			
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>(); 
			params.put("Title",  "title");
			params.put("ItemsJRB",itemsJRB);
			
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream("/reports/Contract.jrxml"); 
			 
			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);
			 
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			 
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params, eventsJRB);
			 
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 
			// 7. Export report
			exporter.export(type, jp, response, baos);
			 
			// 8. Write to reponse stream
			write(token, response, baos);
		
		} catch (JRException jre) {
			throw new RuntimeException(jre);
		}
	}
	
	/**
	* Writes the report to the output stream
	*/
	private void write(String token, HttpServletResponse response,
			ByteArrayOutputStream baos) {
		try {
			
			// Retrieve output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();
			
			// Remove download token
			//tokenService.remove(token);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Event> findIncompleteEvents() {
		// TODO Auto-generated method stub
		return null;
	}
}
