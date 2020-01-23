package com.coviam.bookstore.productMS.service.impl;

import com.coviam.bookstore.productMS.entity.Product;
import com.coviam.bookstore.productMS.repository.ProductRepository;
import com.coviam.bookstore.productMS.service.ProductService;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    ////////TODO     Add comparator
    @Override
    public ArrayList<Product> getTopProducts() {
        ArrayList<Product> productList = (ArrayList<Product>) productRepository.findAll();
        productList=(ArrayList<Product>)productList.stream().sorted().limit(20).collect(Collectors.toList());
        //Page<Login> page= loginRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return productList;
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
        System.out.println("------_>"+id);
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
