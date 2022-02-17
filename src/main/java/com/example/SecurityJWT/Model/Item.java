package com.example.SecurityJWT.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Items")
public class Item {
	
	@Id
	long itemId;
	String itemname; 
	int quantity; 
	String description;
	
	public Item() {
		
	}
	
	public Item(long itemId, String itemname, int quantity, String description) {
		super();
		this.itemId = itemId;
		this.itemname = itemname;
		this.quantity = quantity;
		this.description = description;
	}
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
