package com.mspoc.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mspoc.shoppingcart.entity.Customer;

/**
 * Repository class for handling all database operations for customer type
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

  /**
   * Search customer table with customer id
   * 
   * @param customerId
   * @return
   */
  public Customer findByCustomerId(int customerId);

}
