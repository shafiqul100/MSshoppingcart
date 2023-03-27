package com.mspoc.shoppingcart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mspoc.shoppingcart.entity.CustomerCart;

/**
 * Repository class for handling all database operations for customercart type
 *
 */
@Repository
public interface CustomerCartRepository extends CrudRepository<CustomerCart, String> {

  /**
   * Search customer cart table with customer id
   * 
   * @param customerId
   * @return
   */
  public CustomerCart findByCustomerId(int customerId);

  /**
   * Query to get cart id from customer cart for given customer id
   * 
   * @param customerId
   * @return
   */
  @Query("select cartId from CustomerCart where customerId=:customerId")
  public int findCartIdForCustomer(@Param("customerId") int customerId);
}
