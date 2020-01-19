package com.coviam.bookstore.productMS.Service;

import com.coviam.bookstore.productMS.Entity.Product;

import java.util.HashMap;
import java.util.List;


public interface ProductService {
    Iterable<Product> addProduct(Product product);

    Iterable<Product> deleteProductById(String id);

    Iterable<Product> updateProduct(Product product);

    Product getProductById(String id);

    List<String> getGenre();

     getProductByGenre(String genre);
}
