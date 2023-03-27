package com.mspoc.shoppingcart.dto;

import lombok.Data;

/**
 * DTO class for product to be added to cart
 *
 */
@Data
public class ProductDto {
  private int productId;
  private int productQuantity;
}
