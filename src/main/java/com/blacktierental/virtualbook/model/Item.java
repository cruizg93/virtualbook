package com.blacktierental.virtualbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_item")
public class Item {
/*
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="item_id",referencedColumnName="id")
	private List<EventItem> items;
	*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="description")
	private String description;
	
	@NotEmpty
	@Column(name="state")
	private String state=State.ACTIVE.getState();
	
	@Column(name="quantity")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private ItemType itemTypes;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

		
	public ItemType getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(ItemType itemTypes) {
		this.itemTypes = itemTypes;
	}
	/*
	public List<EventItem> getItems() {
		return items;
	}

	public void setItems(List<EventItem> items) {
		this.items = items;
	}*/

	@Override
	public int hashCode(){
		final int prime =98;
		int result =1;
		result = prime * result + ((id==null)?0:id.hashCode());
		result = prime * result + ((description==null)?0:description.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Item))
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (description== null) {
            if (other.description!= null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
	}
	
	@Override
	public String toString(){
		return "Item[id="+id+",description="+description+",quantity="+quantity+",state="+state+"]";
	}
	
	
}
