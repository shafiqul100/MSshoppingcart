package com.mspoc.shoppingcart.service;

import com.mspoc.msdevkit.exception.MsPlaformException;
import com.mspoc.shoppingcart.dto.CartRequest;
import com.mspoc.shoppingcart.dto.CartResponse;

/**
 * Service class to handle the operations for shopping cart
 *
 */
public interface ShoppingCartService {

  /**
   * Function to add product to cart
   * 
   * @param cartRequest
   * @throws MsPlaformException
   */
  void addProductToCart(CartRequest cartRequest) throws MsPlaformException;

  /**
   * Function to get cart details for the customer
   * 
   * @param customerId
   * @return cartResponse
   * @throws MsPlaformException
   */
  CartResponse getCartDetails(int customerId) throws MsPlaformException;

  /**
   * Function to delete a product from cart of the customer
   * 
   * @param customerId
   * @param productId
   * @throws MsPlaformException
   */
  void deleteProductFromCart(int customerId, int productId) throws MsPlaformException;

}
