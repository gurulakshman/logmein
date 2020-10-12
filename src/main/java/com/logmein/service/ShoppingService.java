package com.logmein.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logmein.dto.ItemDto;
import com.logmein.entity.Item;
import com.logmein.exception.ResourceNotFoundException;
import com.logmein.repository.ShoppingRepository;

/**
 * This is service class for the shopping controller.
 * All the business logic for the shopping controller is written here
 * @author Guru
 *
 */
@Service
public class ShoppingService {
	@Autowired
	ShoppingRepository shoppingRepository;
	/**
	 * This method adds new item to database.
	 * @param item
	 * @return added item
	 */
	public ItemDto addItem(Item item) {
		return shoppingRepository.save(item).toItemDto();
	}
	/**
	 * This method will returns all the items from database. 
	 * @return List of Items
	 */
	public List<ItemDto> getAllItems() {
		List<Item> itemList = (List<Item>) shoppingRepository.findAll();
		List <ItemDto> itemDtoList = itemList.stream().map(Item::toItemDto).collect(Collectors.toList());
		return itemDtoList;
	}
	/**
	 * This method will returns the item details by id.
	 * @param id
	 * @return Item Details
	 */
	public ItemDto findById(Long id) {		
		Item item = shoppingRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Item with id :"+id+" Not Found!"));
		return item.toItemDto();
	}
	/**
	 * This method will update the item details in database.
	 * @param id item id
	 * @param item new Item details
	 * @return Updated Item details
	 */
	public ItemDto updateItem(Long id,Item item) {		
		Item updatedItem =  shoppingRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Item with id :"+id+" Not Found!"));
		updatedItem.setName(item.getName());
		updatedItem.setQuantity(item.getQuantity());
		return shoppingRepository.save(updatedItem).toItemDto();
	}
	/**
	 * This method will delete the item from database.
	 * @param id
	 */
	public void deleteItem(Long id) {
		shoppingRepository.findById(id)
							.orElseThrow(()->new ResourceNotFoundException("Item with id :"+id+" Not Found!"));
		shoppingRepository.deleteById(id);
	}
}
