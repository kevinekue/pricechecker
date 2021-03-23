package com.myretail.pricechecker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.pricechecker.ProductInfo;
import com.myretail.pricechecker.dao.ProductRepository;
import com.myretail.pricechecker.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 3/21/2021
 */

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    ObjectMapper objMapper = new ObjectMapper();

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }


    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) throws IOException {
            Object result = this.productRepo.findByProductId(id);
            System.out.println(this.productRepo.findByProductId(id));
            if(result == null){
                System.out.println("It is null");
                return null;
            } else {
                Product product =  (Product)result;
                product.setName(getProductName(id));
                this.productRepo.save(product);
                return product;
            }

    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody String jsonString) throws IOException {
        System.out.println("TEST" + jsonString);
        Product product = objMapper.readValue(jsonString, Product.class);
        Object dbResult = productRepo.findByProductId(product.getProductId());
        if (dbResult == null) {
            System.out.println("Product ID: " + id + " not found in  the database.");
            return null;
        } else {
            Product dbProduct = (Product) dbResult;
            dbProduct.setCurrent_price(product.getCurrent_price());
            this.productRepo.save(dbProduct);
            System.out.println(product.toString());
            return dbProduct;
        }
    }

//    @GetMapping("/products/target/{id}")
//    public String getProductName(@PathVariable("id")int id) throws IOException {
    public String getProductName(int id) throws IOException {
        return ProductInfo.getProductInfo(id);
    }

}
