package com.coviam.bookstore.productMS.service;

import com.coviam.bookstore.productMS.entity.Product;

import java.util.ArrayList;
import java.util.List;


public interface ProductService {
    String addProduct(Product product);

    void deleteProductById(String id);

    Iterable<Product> updateProduct(Product product);

    Product getProductById(String id);

    List<String> getGenre();

    ArrayList<Product> getProductByGenre(String genre);
    ArrayList<Product> getTopProducts();
}
