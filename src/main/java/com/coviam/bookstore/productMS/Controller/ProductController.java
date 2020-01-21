package com.coviam.bookstore.productMS.Controller;

import com.coviam.bookstore.productMS.DTO.ProductDTO1;
import com.coviam.bookstore.productMS.DTO.ProductDTO2;
import com.coviam.bookstore.productMS.DTO.ProductDTO3;
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
    private ProductService productService;



    /////////TODO       ADD KAFKA
    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDTO1 productDTO1){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO1,product);
        return productService.addProduct(product);
    }


    @DeleteMapping("/deleteProductById/{id}")
    void deleteProductById(@PathVariable("id") String id){

        productService.deleteProductById(id);
    }




    @GetMapping("/getProductById/{id}")
    ProductDTO1 getProductById(@PathVariable("id") String id){
        System.out.println("called");
        Product product=productService.getProductById(id);
        ProductDTO1 productDTO1=new ProductDTO1();
        BeanUtils.copyProperties(product,productDTO1);
        return productDTO1;
    }

    @GetMapping("/getGenreList")
    List<String> getGenre(){
        return productService.getGenre();
    }

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre){

        List<Product> productList=productService.getProductByGenre(genre);
        List<ProductDTO3> dto3List=new ArrayList<ProductDTO3>();
        for(Product product:productList){
            ProductDTO3 productDTO3=new ProductDTO3();
            BeanUtils.copyProperties(product,productDTO3);
            dto3List.add(productDTO3);
        }
        return dto3List;
    }

}
