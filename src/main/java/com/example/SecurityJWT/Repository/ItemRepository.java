package com.example.SecurityJWT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SecurityJWT.Model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
