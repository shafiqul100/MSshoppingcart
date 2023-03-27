package com.mspoc.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mspoc.shoppingcart.repository.CustomerRepository;

/**
 * Implementation class for Customer service
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * Function to validate if customer exists in customer table for the given id
   */
  @Override
  public Boolean customerExists(int customerId) {
    if (null != customerRepository.findByCustomerId(customerId)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

}
