package com.example.SecurityJWT.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SecurityJWT.Model.Item;
import com.example.SecurityJWT.Repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	public ResponseEntity<?> getAllItems(){
		List<Item> items = itemRepository.findAll();
		if(items.size()==0)
			return new ResponseEntity<String>("Items list empty",HttpStatus.OK);
		return new ResponseEntity<List<Item>>(items,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getItemById(long id){
		Optional<Item> item = itemRepository.findById(id);
		
		if(item==null)
			return new ResponseEntity<String>("Items not found",HttpStatus.NOT_FOUND);
		
		return  new ResponseEntity<Item>(item.get(),HttpStatus.OK);
	}
	
	public ResponseEntity<?> addItem(Item item){
		Item added = itemRepository.save(item);
		return new ResponseEntity<Item>(added,HttpStatus.OK);
		
	}

}
