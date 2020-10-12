package com.logmein.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.logmein.entity.Item;
/**
 * This is repository class for the table Item .
 * This class does all the operations on the table Item.
 * @author Guru
 *
 */
@Repository
public interface ShoppingRepository extends CrudRepository<Item,Long> {

}
