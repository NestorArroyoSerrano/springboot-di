package com.nestor.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestor.springboot.di.app.springbootdi.models.Product;

public class ProductRepositoryJson implements ProductRepository {

   private List<Product> list;


    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json"); // Ya no nos sirve porque lo pasamos en AppConfig con el @Value vía constructor
        readValueJson(resource);
    }
    public ProductRepositoryJson(Resource resource) {
      readValueJson(resource);
    }
    private void readValueJson(Resource resource) {
        // Resource resource = new ClassPathResource("json/product.json"); // Ya no nos sirve porque lo pasamos en AppConfig con el @Value vía constructor
         ObjectMapper objectMapper = new ObjectMapper();
         try {
             list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));;
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p-> 
             p.getId().equals(id)).findFirst().orElseThrow();
        
    }

}
