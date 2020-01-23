package com.coviam.bookstore.productMS.controller;

import com.coviam.bookstore.productMS.dto.ProductDTO1;
import com.coviam.bookstore.productMS.dto.ProductDTO3;
import com.coviam.bookstore.productMS.dto.SearchDTO;
import com.coviam.bookstore.productMS.entity.Product;
import com.coviam.bookstore.productMS.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private KafkaTemplate<String, SearchDTO> kafkaTemplate;
    private static final String TOPIC = "kafkaAdd";

    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDTO1 productDTO1){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO1,product);
        String response=productService.addProduct(product);
        SearchDTO searchDto=new SearchDTO();
        BeanUtils.copyProperties(productDTO1,searchDto);
        kafkaTemplate.send(TOPIC, searchDto);
        return response;
    }

    @GetMapping("/getTopProducts")
    List<ProductDTO1> getTopProducts(){
        List<ProductDTO1> listOfProductDTO = new ArrayList<ProductDTO1>();
        List<Product> productList=productService.getTopProducts();
        for(Product product:productList){
            ProductDTO1 productDTO1=new ProductDTO1();
            BeanUtils.copyProperties(product,productDTO1);
            listOfProductDTO.add(productDTO1);
        }
        return listOfProductDTO;

    }


    @DeleteMapping(value = "/deleteProductById/{id}")
    void deleteProductById(@PathVariable("id") String id){

        productService.deleteProductById(id);
    }




    @GetMapping("/getProductById/{id}")
    ProductDTO1 getProductById(@PathVariable("id") String id){
        System.out.println("called---__>"+id);
        Product product=productService.getProductById(id);
        System.out.println("Product :"+product);
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
