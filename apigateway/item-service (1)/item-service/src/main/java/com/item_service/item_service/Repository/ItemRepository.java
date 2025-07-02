package com.item_service.item_service.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.item_service.item_service.Entity.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	Item findByName(String name);

}
