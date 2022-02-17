package com.example.SecurityJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecurityJWT.Model.Item;
import com.example.SecurityJWT.Service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/items")
	public ResponseEntity<?> getAllItems(){
		return itemService.getAllItems();
	}
	
	@GetMapping("items/{id}")
	public ResponseEntity<?> getItemById(@PathVariable long id){
		return itemService.getItemById(id);
	}
	
	@PostMapping("/additem")
	public ResponseEntity<?> addItem(@RequestBody Item item){
		return itemService.addItem(item);
	}

}
