package com.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.entities.Item;
import com.todolist.repositories.ItemRepository;

@Service
public class ItemService {
	
	public final ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public List<Item> getAllItems() {
		return (List<Item>) itemRepository.findAll();
	}
	
	public Item getItem(Long id) {
		
		Optional<Item> optionalItem = itemRepository.findById(id);
		
		return optionalItem.isPresent() ? optionalItem.get() : null;
	}
	
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	public void deleteItem(Long itemId) {
		itemRepository.deleteById(itemId);
	}

}
