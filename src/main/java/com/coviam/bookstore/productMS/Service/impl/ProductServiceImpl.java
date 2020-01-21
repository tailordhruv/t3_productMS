package com.coviam.bookstore.productMS.Service.impl;

import com.coviam.bookstore.productMS.Entity.Product;
import com.coviam.bookstore.productMS.Repository.ProductRepository;
import com.coviam.bookstore.productMS.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(Product product) {

        return productRepository.save(product).getProductId();
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);

    }

    @Override
    public Iterable<Product> updateProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        System.out.println(id);
        Optional<Product> o= productRepository.findById(id);
        System.out.println(o);
        return o.get();
    }

    @Override
    public  ArrayList<Product> getProductByGenre(String genre)
    {


        List<Product> productList=(ArrayList<Product>)productRepository.findAll();
        return (ArrayList<Product>) productList.stream().filter(product -> product.getGenre().equals(genre)).collect(Collectors.toList());


    }

    @Override
    public List<String> getGenre() {

        List<Product> listOfProduct = (ArrayList<Product>)productRepository.findAll();
        return listOfProduct.stream().map(e -> e.getGenre()).distinct().collect(Collectors.toList());
    }
}
