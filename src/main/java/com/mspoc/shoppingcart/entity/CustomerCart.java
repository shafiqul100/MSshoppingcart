package com.mspoc.shoppingcart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * customercart table entity mapping
 *
 */
@Entity
@Table(name = "customercart")
@Getter
@Setter
@ToString
public class CustomerCart {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_TOKEN")
  @SequenceGenerator(name = "CART_TOKEN", initialValue = 1, allocationSize = 1)
  private int cartId;
  private int customerId;
}
