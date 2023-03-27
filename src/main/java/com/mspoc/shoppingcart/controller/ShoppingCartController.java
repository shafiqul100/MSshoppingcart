package com.mspoc.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mspoc.msdevkit.exception.MsPlaformException;
import com.mspoc.shoppingcart.dto.CartRequest;
import com.mspoc.shoppingcart.dto.CartResponse;
import com.mspoc.shoppingcart.dto.ProductResponse;
import com.mspoc.shoppingcart.service.ShoppingCartService;
import com.mspoc.shoppingcart.service.client.ProductFeignClient;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * This controller class handles APIs related to customer's shopping cart.
 *
 */
@RestController
@Slf4j
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService cartService;

  @Autowired
  private ProductFeignClient productFeignClient;

  /**
   * Function to fetch the customer's shopping cart details.
   * 
   * @param customerId
   * @return list of products with quantity and total cart price
   * @throws MsPlaformException
   */
  @GetMapping("/cart")
  public CartResponse viewCustomerCart(@RequestParam int customerId) throws MsPlaformException {
    log.info("Inside view customer shopping cart function");
    return cartService.getCartDetails(customerId);
  }

  /**
   * Function to add product to customer's cart. Product id and qunatity is required.
   * 
   * @param cartRequest
   * @return
   * @throws MsPlaformException
   */
  @PostMapping("/cart/add")
  public ResponseEntity<String> addProductToCart(@Valid @RequestBody CartRequest cartRequest)
      throws MsPlaformException {
    log.info("Inside add product to shopping cart function");
    cartService.addProductToCart(cartRequest);
    return new ResponseEntity<>("Product has been added to the shopping cart", HttpStatus.CREATED);
  }

  /**
   * Delete any product from shopping cart of the customer.
   * 
   * @param customerId
   * @param productId
   * @return
   * @throws MsPlaformException
   */
  @DeleteMapping("/cart/delete")
  public ResponseEntity<String> deleteProductFromCart(@RequestParam int customerId,
      @RequestParam int productId) throws MsPlaformException {
    log.info("Inside delete product function");
    cartService.deleteProductFromCart(customerId, productId);
    return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
  }

  /**
   * Function to test connectivity with product microservice (inter-service communication)
   * 
   * @param productId
   * @return product response
   * @throws MsPlaformException
   */
  @GetMapping("/cart/product")
  public ProductResponse getProductDetailsViaShoppingCart(@RequestParam int productId)
      throws MsPlaformException {
    return productFeignClient.getProduct(productId);
  }

}
