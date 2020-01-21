package com.coviam.bookstore.productMS.Repository;

import com.coviam.bookstore.productMS.Entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product,String> {

    List<Product> findByProductIdIn(List<String> productIds);


}
