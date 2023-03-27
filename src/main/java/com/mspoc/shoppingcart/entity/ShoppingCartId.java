package com.mspoc.shoppingcart.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Id Class declaration for shopping cart table primary keys
 *
 */
@Data
public class ShoppingCartId implements Serializable {
  private int cartId;
  private int productId;

  public ShoppingCartId() {
    this.cartId = 0;
    this.productId = 0;
  }

  public ShoppingCartId(int cartId, int productId) {
    this.cartId = cartId;
    this.productId = productId;
  }

}
