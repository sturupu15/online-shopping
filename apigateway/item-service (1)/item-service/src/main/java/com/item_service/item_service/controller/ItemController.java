package com.item_service.item_service.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item_service.item_service.Entity.Item;
import com.item_service.item_service.Repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
private ItemRepository itemRepository;
	@GetMapping
public List<Item> getAllItems(){
	return itemRepository.findAll();
}
	@GetMapping("/{name}")
	public Item getItemByName(@PathVariable String name) {
		return itemRepository.findByName(name);
	}
}
