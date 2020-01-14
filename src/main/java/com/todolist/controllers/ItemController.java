package com.todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.entities.Item;
import com.todolist.services.ItemService;

@RestController
public class ItemController {

	private final ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@PutMapping("/item")
	public ResponseEntity createItem(@RequestBody Item item) {
		
		try {
			itemService.saveItem(item);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PostMapping("/item")
	public ResponseEntity updateItem(@RequestBody Item item) {
		
		try {
			itemService.saveItem(item);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity deleteItem(@PathVariable("id") Long itemId) {
		
		try {
			itemService.deleteItem(itemId);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<Item> getItem(@PathVariable("id") Long itemId) {
		
		try {
			Item item = itemService.getItem(itemId);
			
			if (item != null) {
				return ResponseEntity.ok(item);
			}
			else {
				return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getItems() {
		
		try {
			List<Item> items = itemService.getAllItems();
			
			if (items != null) {
				return ResponseEntity.ok(items);
			}
			else {
				return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<Item>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
