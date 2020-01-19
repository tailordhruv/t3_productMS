package com.coviam.bookstore.productMS.Controller;

import com.coviam.bookstore.productMS.DTO.ProductDTO1;
import com.coviam.bookstore.productMS.DTO.ProductDTO2;
import com.coviam.bookstore.productMS.DTO.Product_Merchant_DTO;
import com.coviam.bookstore.productMS.Entity.Product;
import com.coviam.bookstore.productMS.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /////////TODO       ADD KAFKA
    @PostMapping("/addProduct")
    List<Product> addProduct(@RequestBody ProductDTO1 productDTO1){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO1,product);
        return (ArrayList<Product>)productService.addProduct(product);
    }


    @DeleteMapping("/deleteProductById/{id}")
    List<Product> deleteProductById(@PathVariable("id") String id){
        return (ArrayList<Product>)productService.deleteProductById(id);
    }


    @PostMapping("/updateProduct")             ////////////TODO How to Update
    List<Product> updateProduct(@RequestBody ProductDTO1 productDTO1){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO1,product);
        return (ArrayList<Product>)productService.updateProduct(product);
    }

    @GetMapping("/getProductById/{id}")//////////TODO
    ProductDTO2 getProductById(@PathVariable("id") String id){
        Product product=productService.getProductById(id);
        ProductDTO2 productDTO2=new ProductDTO2();
        Product_Merchant_DTO product_merchant_dto=(new RestTemplate()).getForObject("http://localhost:8084/merchant/getProductMerchant/", Product_Merchant_DTO.class);
        BeanUtils.copyProperties(product_merchant_dto,productDTO2);
        BeanUtils.copyProperties(product,productDTO2);
        return productDTO2;
    }

    @GetMapping("/getGenreList")
    List<String> getGenre(){
        return productService.getGenre();
    }

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO2> getProductByGenre(@PathVariable("genre") String genre){
        ProductDTO2 productDTO2=new ProductDTO2();
        productService.getProductByGenre(genre);
    }

}
