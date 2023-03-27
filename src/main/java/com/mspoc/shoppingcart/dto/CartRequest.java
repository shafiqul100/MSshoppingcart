package com.mspoc.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO class for cart request
 *
 */
@Data
public class CartRequest {
  @NotNull(message = "The customer id is required")
  @Min(value = 0, message = "The customer id must be a positive number")
  private int customerId;

  @NotNull(message = "The product id is required")
  @Min(value = 0, message = "The product id must be a positive number")
  private int productId;

  @NotNull(message = "The product quantity is required")
  @Min(value = 0, message = "The product quantity must be a positive number")
  private int productQuantity;
}
