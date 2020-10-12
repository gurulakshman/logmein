package com.logmein.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.logmein.dto.ItemDto;
/**
 * This is entity class for the table Item.
 * @author Guru
 *
 */
@Entity
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native" )
    @GenericGenerator(name = "native", strategy = "native")	
	private Long id;
	
	private String name;
	
	private Long quantity;
	
	
	public Item() {
		
	}

	public Item(String name, Long quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public Item(Long id, String name, Long quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public ItemDto toItemDto() {
		return new ItemDto(this.id, this.name, this.quantity);
	}
}