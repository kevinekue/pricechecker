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
    RestTemplate restTemplate;
    //    LocalDateTime currentTime = LocalDateTime.now();
    ObjectMapper objMapper = new ObjectMapper();

    private ProductRepository productRepo;

    public ProductController() {
    }

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }


    @PostMapping("/products/")
    public String addProduct(@RequestBody String jsonString) throws JsonProcessingException {

        Product product = objMapper.readValue(jsonString, Product.class);

        if (productRepo.findProductByProductId(product.getProductId()) == null) {
            this.productRepo.insert(product);
//            product = updatePriceData(product);
            System.out.println(product.toString());
            return product.toString();
        } else {
            return "We've identified an existing ID in your request. " +
                    "\nIf you'd like to update  the pricing information of a product, please send a PUT request with an ID path variable following this URL format \\products\\{id}. " +
                    "\nOtherwise, please assign a different ID to your new entry. \n Thank you.";
            //TODO: Consider handling of existing duplicate product_id in the db
            //TODO: Lookout for leading '0' in the ID
        }
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        Product product = this.productRepo.findProductByProductId(id);
        System.out.println(product.toString());
        return product;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody String jsonString) throws JsonProcessingException {
        if (productRepo.findProductByProductId(id) == null) {
            System.out.println("Product ID: " + id + " not found in  the database.");
            return null;
        } else {
            Product product = objMapper.readValue(jsonString, Product.class);
            Product dbProduct = productRepo.findProductByProductId(product.getProductId());
            dbProduct.setCurrent_price(product.getCurrent_price());
            this.productRepo.save(dbProduct);
            System.out.println(product.toString());
            return dbProduct;
        }
    }
    /**
     private Product updatePriceData(Product product) {
     Map<LocalDateTime, Double> priceUpdate = new HashMap();
     priceUpdate.put(currentTime, product.getCurrent_price().getValue());


     Queue priceHistory = product.getCurrent_price().getPriceHistory();

     if (priceHistory.size() == 5) {
     priceHistory.poll();
     }
     priceHistory.add(priceUpdate);


     System.out.println(product.getCurrent_price().getValue());
     //Update Currency
     product.getCurrent_price().setCurrency("USD");

     //TODO: Make the currency change dynamic.
     // Consider implementing a currency converter api.


     return product;
     }
     */

    @GetMapping("/products/target/{id}")
    public String getProductInfo(@PathVariable("id")int id) throws IOException {
//          13860428, 54456119, 13264003, 12954218
        return ProductInfo.getProductInfo(id);
    }

}
