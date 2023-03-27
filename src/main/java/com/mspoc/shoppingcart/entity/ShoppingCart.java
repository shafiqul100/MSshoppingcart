package com.mspoc.shoppingcart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Shopping cart table entity mapping
 *
 */
@Entity
@Table(name = "shoppingcart")
@IdClass(value = ShoppingCartId.class)
@Getter
@Setter
@ToString
public class ShoppingCart {

  @Id
  private int cartId;
  @Id
  private int productId;
  private int productQuantity;

}
