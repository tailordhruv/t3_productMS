package com.coviam.bookstore.productMS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product_Merchant_DTO {
    private String productId;
    private  String merchantId;
    private  String price;
    private  String quantity;

}
