package com.mspoc.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mspoc.shoppingcart.repository.CustomerCartRepository;

/**
 * Implementation class for Customer Cart service
 *
 */
@Service
public class CustomerCartServiceImpl implements CustomerCartService {

  @Autowired
  private CustomerCartRepository customerCartRepository;

  /**
   * Function to get the cart id from customer table
   */
  @Override
  public int getCartIdForCustomer(int customerId) {
    return customerCartRepository.findCartIdForCustomer(customerId);
  }

}
