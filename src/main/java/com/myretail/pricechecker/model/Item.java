package com.myretail.pricechecker.model;

import com.sun.org.apache.bcel.internal.generic.ObjectType;
import net.minidev.json.annotate.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Kevin Ekue created on 3/21/2021
 */


@Document(collection="items")
public class Item {


    @Id
    @JsonIgnore
    private ObjectId _id;

//    @Indexed(unique = true)

    private int id;
    private String name;
    Price current_price;
/**
   public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Item(int id, String name, Price current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }

    public Item() {
    }
}
