package com.myretail.pricechecker.dao;

import com.myretail.pricechecker.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kevin Ekue created on 3/21/2021
 */

@Repository
public interface ProductRepository extends MongoRepository<Product,Integer> {
    Product findProductByProductId(int productId);
}
