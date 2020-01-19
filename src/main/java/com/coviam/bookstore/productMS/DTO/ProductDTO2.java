package com.coviam.bookstore.productMS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ProductDTO2 {

    String product_id;
    String product_name;
    String Genere;
    String rating;
    HashMap<String,String> Attributes;
    String description;
    String author;
    String url;
    String isbn;
    String price;
    String quantity;
}
