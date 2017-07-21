package com.blacktierental.virtualbook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="tbl_attachment")
public class Attachment {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="description")
	private String description;

	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name="tbl_attachment_item",
				joinColumns = {@JoinColumn(name="attachment_id")},
				inverseJoinColumns = {@JoinColumn(name="item_id")})
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Item> getItemAttachments() {
		return items;
	}

	public void setItemAttachments(List<Item> items) {
		this.items= items;
	}

	@Override
	public int hashCode(){
		final int prime = 44;
		int result = 1;
		result = prime * result + ((id==null)?0:id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this== obj)
			return true;
		if(obj==null)
			return false;
		if(!(obj instanceof Attachment))
			return false;
		Attachment other = (Attachment) obj;
		if( id == null){
			if(other.id!= null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Attachment [id="+id+",description="+description+"]";
	}
}
