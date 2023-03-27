package com.mspoc.shoppingcart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer table entity mapping
 *
 */
@Entity
@Getter
@Setter
@ToString
public class Customer {
  @Id
  private int customerId;
  private String customerName;
}
