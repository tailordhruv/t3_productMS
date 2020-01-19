package com.coviam.bookstore.productMS.Service.impl;

import com.coviam.bookstore.productMS.Entity.Product;
import com.coviam.bookstore.productMS.Repository.ProductRepository;
import com.coviam.bookstore.productMS.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> addProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> deleteProductById(String id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> updateProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public  getProductByGenre(String genre) {

    }

    @Override
    public List<String> getGenre() {

        List<Product> listOfProduct = (ArrayList<Product>)productRepository.findAll();
        return listOfProduct.stream().map(e -> e.getGenere()).distinct().collect(Collectors.toList());
    }
}
