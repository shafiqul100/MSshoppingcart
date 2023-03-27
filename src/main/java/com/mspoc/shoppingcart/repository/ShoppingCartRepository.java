package com.mspoc.shoppingcart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mspoc.shoppingcart.entity.ShoppingCart;

/**
 * Repository class for handling all database operations for shoppingcart type
 *
 */
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {

  /**
   * Search shopping cart table with cart id
   * 
   * @param cartId
   * @return
   */
  public List<ShoppingCart> findByCartId(int cartId);

  /**
   * Search shopping cart table with cart id and product id
   * 
   * @param cartId
   * @param productId
   */
  public void deleteByCartIdAndProductId(int cartId, int productId);
}
