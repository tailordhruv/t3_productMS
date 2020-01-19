package com.coviam.bookstore.productMS.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String product_id;
    String product_name;
    String Genere;
    String rating;
    HashMap<String,String> Attributes;
    String description;
    String author;
    String url;
    String isbn;


}
