package com.myretail.pricechecker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Kevin Ekue created on 3/21/2021
 */


@Document(collection="Products")
public class Product {

    @Id
    private ObjectId _id;
    @Indexed
    private int productId;
    private String name;
    Price current_price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Price current_price) {
        this.current_price = current_price;
    }

    public Product(int productId, String name, Price current_price) {
        this.productId = productId;
        this.name = name;
        this.current_price = current_price;
    }

    public Product() {
    }
}
