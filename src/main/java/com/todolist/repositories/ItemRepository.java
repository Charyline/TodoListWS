package com.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todolist.entities.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
