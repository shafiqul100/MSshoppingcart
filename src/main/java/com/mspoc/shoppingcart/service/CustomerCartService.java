package com.mspoc.shoppingcart.service;

/**
 * Service class to handle the operations for customer cart
 *
 */
public interface CustomerCartService {

  /**
   * Function to get the cart id from customer table
   * 
   * @param customerId
   * @return cart id
   */
  public int getCartIdForCustomer(int customerId);
}
