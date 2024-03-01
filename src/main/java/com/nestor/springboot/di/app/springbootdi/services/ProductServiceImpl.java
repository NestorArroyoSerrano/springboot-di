package com.nestor.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nestor.springboot.di.app.springbootdi.models.Product;
import com.nestor.springboot.di.app.springbootdi.repositories.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{

   // private Environment environment;

    @Value("${config.price.tax}")
    private Double tax;

    private ProductRepository repository;

    
    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository/*, Environment environment*/) {
        this.repository = repository;
       // this.environment = environment;
    }
    

    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Product> findAll() {
    return repository
    .findAll()
    .stream()
     .map(p-> {
   // System.out.println(environment.getProperty("config.price.tax", Double.class));
    System.out.println(tax);
     Double priceTax= p.getPrice() * tax;
     Product newProd = (Product) p.clone();
     newProd.setPrice(priceTax.longValue());
     return newProd;

     /* 
   p.setPrice(priceTax.longValue());
    return p;
    */
}).collect(Collectors.toList());
    
}
public Product findById(Long id) {
    return repository.findById(id);
    }
}
