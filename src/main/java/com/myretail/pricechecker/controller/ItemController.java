package com.myretail.pricechecker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.pricechecker.dao.ItemRepository;
import com.myretail.pricechecker.model.Item;
import com.myretail.pricechecker.model.Price;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author Kevin Ekue created on 3/21/2021
 */

@RestController
public class ItemController {
    LocalDateTime currentTime = LocalDateTime.now();
    ObjectMapper objMapper = new ObjectMapper();

    private ItemRepository itemRepo;

    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/products")
    public List<Item> getItems() {
        return this.itemRepo.findAll();
    }


    @PostMapping("/products/")
    public String addItem(@RequestBody String jsonString) throws JsonProcessingException {

        Item item = objMapper.readValue(jsonString, Item.class);

        if (itemRepo.findItemById(item.getId()) == null) {
            this.itemRepo.insert(item);
            item = updatePriceData(item);
            System.out.println(item.toString());
            return item.toString();
        } else {
            return "We've identified an existing ID in your request. " +
                    "\nIf you'd like to update  the pricing information of an ID, please send a PUT request with an ID path variable following this URL format \\products\\{id}. " +
                    "\nOtherwise, please assign a different ID to your new entry. \n Thank you.";
            //TODO: Consider handling of existing duplicate itemID in the db
            //TODO: Lookout for leading '0' in the ID
        }
    }

    @GetMapping("/products/{id}")
    public Item getItem(@PathVariable("id") int id) {
        Item item = this.itemRepo.findItemByIdEquals(id);
//        .toString();
        System.out.println(item.toString());
        return item;
    }

    @PutMapping("/products/{id}")
    public Item updateItem(@PathVariable("id") int id, @RequestBody String jsonString) throws JsonProcessingException {
        if(itemRepo.findItemById(id) == null){
            System.out.println("Item ID: "+ id + " not found in  the database.");
            return null;
        } else {
            Item item = objMapper.readValue(jsonString, Item.class);
            Item dbItem = itemRepo.findItemById(item.getId());
            dbItem.setCurrent_price(item.getCurrent_price());
            this.itemRepo.save(dbItem);
            System.out.println(item.toString());
            String output = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dbItem);

            return dbItem;
        }

    }

    private Item updatePriceData(Item item) {
        Map<LocalDateTime, Double> priceUpdate = new HashMap();
        priceUpdate.put(currentTime, item.getCurrent_price().getValue());

        /*
        Queue priceHistory = item.getCurrent_price().getPriceHistory();

        if (priceHistory.size() == 5) {
            priceHistory.poll();
        }
        priceHistory.add(priceUpdate);
        */

        System.out.println(item.getCurrent_price().getValue());
        //Update Currency
        item.getCurrent_price().setCurrency("USD");

        //TODO: Make the currency change dynamic.
        // Consider implementing a currency converter api.


        return item;
    }
}
