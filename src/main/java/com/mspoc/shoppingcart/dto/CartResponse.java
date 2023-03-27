package com.mspoc.shoppingcart.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO class for cart response
 *
 */
@Data
public class CartResponse {
  private List<ProductDto> products;
  private Integer cartPrice;
}
