package com.nestor.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.nestor.springboot.di.app.springbootdi.models.Product;
import com.nestor.springboot.di.app.springbootdi.repositories.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService{

    private ProductRepositoryImpl repository = new ProductRepositoryImpl();

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
