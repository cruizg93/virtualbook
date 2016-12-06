package com.blacktierental.virtualbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_item_type")
public class ItemType {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="description",length=20,unique=true, nullable=false)
	private String description = ItemTypeDescription.MAIN.getItemTypeDescription();
	
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

	@Override
	public int hashCode(){
		final int prime = 35;
		int result = 1;
		result = prime * result + ((id==null)?0:id.hashCode());
		result = prime * result + ((description==null)?0:description.hashCode());
		return result;
	}
	
	@Override
	public boolean equals( Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof ItemType))
			return false;
		
		ItemType other = (ItemType)obj;
		if(id==null){
			if(other.id != null)
				return false;
		}else if (!id.equals(other.id))
			return false;
		if(description == null){
			if(other.description != null)
				return false;
		}else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "ItemType [id="+id+",description="+description+"]";
	}
}
