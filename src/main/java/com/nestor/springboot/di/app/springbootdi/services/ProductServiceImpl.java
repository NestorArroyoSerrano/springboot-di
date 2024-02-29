package com.nestor.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nestor.springboot.di.app.springbootdi.models.Product;
import com.nestor.springboot.di.app.springbootdi.repositories.ProductRepositoryImpl;
@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepositoryImpl repository;

    @Override
    public List<Product> findAll() {
    return repository
    .findAll()
    .stream()
    .map(p-> {
   Double priceTax= p.getPrice() * 1.25d;
   Product newProd = (Product) p.clone();
   newProd.setPrice(priceTax.longValue());
   return newProd;
}).collect(Collectors.toList());
    
}
public Product findById(Long id) {
    return repository.findById(id);
    }
}
