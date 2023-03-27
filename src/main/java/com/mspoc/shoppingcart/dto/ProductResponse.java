package com.mspoc.shoppingcart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class for product response to store information coming from product microservice
 *
 */
@Getter
@Setter
@ToString
public class ProductResponse {
  private int productId;
  private String productName;
  private String productCategory;
  private String productDescription;
  private int productPrice;
  private int discountId;
  private int productAvailable;
}
