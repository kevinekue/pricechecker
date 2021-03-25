package com.myretail.pricechecker.controller;

import com.myretail.pricechecker.PricecheckerApplication;
import com.myretail.pricechecker.dao.ProductRepository;
import com.myretail.pricechecker.model.Price;
import com.myretail.pricechecker.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.annotation.processing.SupportedSourceVersion;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@DataMongoTest
class ProductControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void getProduct() throws Exception{
        int id = 1234;
        Product productA = new Product();
        productA.setProductId(id);
        productA.setName("Random Stuff");
        Price priceA = new Price();
        priceA.setCurrency("USD");
        priceA.setValue(20.00);
        productA.setCurrent_price(priceA);
//        productRepository.save(productA);

//        given(productRepository.findByProductId(id)).willReturn(productA);

//        mockMVC.perform(MockMvcRequestBuilders.get("/products/1234").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        Assertions.assertEquals(1234, productRepository.findByProductId(1234).getProductId());
    }

    @Test
    void updateProduct() {
    }

}