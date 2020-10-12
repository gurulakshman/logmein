package com.logmein.dto;
/**
 * This class is data transfer Object for Item. 
 * @author Guru 
 *
 */
public class ItemDto {
	
	private Long id;
	private String name;
	private Long quantity;

	public ItemDto() {
		
	}

	public ItemDto(Long id, String name, Long quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
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
}
