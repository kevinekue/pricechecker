package com.myretail.pricechecker.dao;

import com.myretail.pricechecker.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Kevin Ekue created on 3/21/2021
 */

@Repository
public interface ItemRepository extends MongoRepository<Item,Integer> {
    Item findItemById(int id);
    Item findItemByIdEquals(int id);
    Item findPriceHistoryOfItemById(int id);
    Item insert(Item item);
}
