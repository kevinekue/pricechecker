package com.myretail.pricechecker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.pricechecker.exceptions.NonExistentProductIdException;
import com.myretail.pricechecker.ProductInfo;
import com.myretail.pricechecker.exceptions.RequestAndProductIdMismatchException;
import com.myretail.pricechecker.dao.ProductRepository;
import com.myretail.pricechecker.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Kevin Ekue created on 3/21/2021
 */

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    ObjectMapper objMapper = new ObjectMapper();

    /**
     *
     * @return List<Product>
     * This GET mapping returns a list of all the products present in the MyRetail.Products database.
     * Ex: [
     *     {
     *         "productId": 54456119,
     *         "name": null,
     *         "current_price": {
     *             "value": 700,
     *             "currency": "USD"
     *         }
     *     },
     *     {
     *         "productId": 13860428,
     *         "name": null,
     *         "current_price": {
     *             "value": 72,
     *             "currency": "USD"
     *         }
     *     }]
     * */

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    /**
     *
     * @param id Product Id
     * @return Product object if "id" parameter is found in the database.
     * @throws IOException Thrown for IO Exceptions
     * @throws NonExistentProductIdException Thrown for invalid Product Id
     */
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) throws IOException, NonExistentProductIdException {
        try {
            Product product = this.productRepo.findByProductId(id);
            if (product == null) {
                throw new NonExistentProductIdException("Product Id not recognized");
            }
            product.setName(getProductName(id));
            this.productRepo.save(product);
            return product;
        } catch (NonExistentProductIdException e) {
            throw new NonExistentProductIdException("This Product Id(" + id + ") doesn't exist in MyRetail.Products database.");
        }

    }

    /**
     *
     * @param id product id
     * @param jsonString Json Body included in the http request
     * @return Updated Product object with latest prices included in the jsonString parameter included in the RequestBody
     * @throws IOException Thrown for IO Exceptions
     * @throws NonExistentProductIdException Thrown for invalid Product Id
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody String jsonString) throws IOException, NonExistentProductIdException {
        Product product = objMapper.readValue(jsonString, Product.class);

        if(product.getProductId() != id){
            throw new RequestAndProductIdMismatchException("The URL ID Variable: '"+id+"' differs from the Product ID: '"+ product.getProductId()+"'");
        }

        try {
            Product dbProduct = productRepo.findByProductId(id);
            if (dbProduct == null) {
                throw new NonExistentProductIdException("This Product Id(" + id + ") doesn't exist in the 'MyRetail.Products' database.");
            }
            dbProduct.setCurrent_price(product.getCurrent_price());
            this.productRepo.save(dbProduct);
            System.out.println(product.toString());
            return dbProduct;
        } catch (NonExistentProductIdException e) {
            throw new NonExistentProductIdException("This Product Id(" + id + ") doesn't exist in the 'MyRetail.Products' database.");
        }
    }

    /**
     *
     * @param id Product Id
     * @return Title/Name (String) of the Product object associated with the "id" parameter passed in
     * @throws IOException Thrown for IO Exceptions
     * @throws NonExistentProductIdException Thrown for invalid Product Id
     */
    public String getProductName(int id) throws IOException, NonExistentProductIdException {
        return ProductInfo.getProductInfo(id);
    }

}
