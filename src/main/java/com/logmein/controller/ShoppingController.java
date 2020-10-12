package com.logmein.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logmein.dto.ItemDto;
import com.logmein.entity.Item;
import com.logmein.service.ShoppingService;
/**
 * This is controller class for the shopping service
 * @author Guru
 *
 */
@RestController
@RequestMapping("/shopping")
public class ShoppingController {
	@Autowired
	ShoppingService shoppingService;
	/**
	 * This method adds new item to the shopping list. 
	 * @param item 
	 * @return ResponseEntity 
	 */
	@PostMapping
	public ResponseEntity<?> addItem(@RequestBody Item item) {		
		ItemDto addedItemDto = shoppingService.addItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(addedItemDto.getId())
                            .toUri();
        return ResponseEntity.created(location).build();
	}
	/**
	 * This method return all the items in the shopping list. 
	 * @return list of all Items
	 */
	@GetMapping
	public List<ItemDto> getAllItems() {		
		return shoppingService.getAllItems();
	}
	/**
	 * This method will returns item by id
	 * @param id
	 * @return Item detail
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ItemDto> findById(@PathVariable Long id)  {		
		return ResponseEntity.ok().body(shoppingService.findById(id));
	}
	/**
	 * This method update the shopping item. 
	 * @param id of the item
	 * @param item updated item details
	 * @return updated Item details
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ItemDto> updateItem(@PathVariable Long id,@RequestBody Item item) {		
		return ResponseEntity.ok().body(shoppingService.updateItem(id,item));
	}
	/**
	 * This method will delete the item form the shopping list based on id.
	 * @param id of the item to be deleted.
	 */
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable Long id) {		
		shoppingService.deleteItem(id);
		ResponseEntity.ok(); 
	}
}
