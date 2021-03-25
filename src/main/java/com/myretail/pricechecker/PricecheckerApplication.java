package com.myretail.pricechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;


@SpringBootApplication
public class PricecheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricecheckerApplication.class, args);
    }

    /**
     *
     * @return Populates the MyRetail.Products database with the data contained in "resources\price-data.json"
     * upon the start of the application
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean populateProductRepo() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("price-data.json")});
        return factory;
    }
}
