package com.mspoc.shoppingcart.service;

/**
 * Service class to handle the operations for customer
 *
 */
public interface CustomerService {

  /**
   * Function to validate if customer exists in customer table for the given id
   * 
   * @param customerId
   * @return true/false
   */
  Boolean customerExists(int customerId);
}
