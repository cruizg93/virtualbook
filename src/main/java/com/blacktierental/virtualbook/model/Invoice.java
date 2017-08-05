package com.blacktierental.virtualbook.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_invoice")
public class Invoice {

	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST})
	@JoinTable(name="tbl_invoice_event",
				joinColumns = {@JoinColumn(name="invoice_id")},
				inverseJoinColumns = {@JoinColumn(name="event_id")})
	private Set<Event> events = new HashSet<Event>();
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	@Column(name="due_date")
	private LocalDate dueDate;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@NotEmpty
	@Column(name="state",nullable=false)
	private String state = State.ACTIVE.getState();

	@Column(name="comment")
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getTotal(){
		Double total = 0D;
		if(events != null && !events.isEmpty()){
			for(Event e: events){
				total += e.getTotal();
			}
		}
		return total;
	}
	
	public boolean isCollected(){
		Double paid = 0D;
		Double total = getTotal();
		if(total>0){
			if(events != null && !events.isEmpty()){
				for(Event e: events){
					paid += e.getAdvance();
				}
			}
			if(paid>=total){
				return true;
			}
		}else{
			return true;
		}
		return false;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode(){
		final int prime = 123;
		int result = 1;
		result = prime * result + ((id==null)?0:id.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		return "Invoice [id="+id+",Due Date="+dueDate+"]";
	}
	
	@Override
	public boolean equals(Object obj){
		if(this== obj)
			return true;
		if(obj==null)
			return false;
		if(!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		if( id == null){
			if(other.id!= null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		return true;
	}
}
