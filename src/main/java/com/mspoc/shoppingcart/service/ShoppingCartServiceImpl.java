package com.mspoc.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mspoc.msdevkit.exception.MsPlaformException;
import com.mspoc.shoppingcart.dto.CartRequest;
import com.mspoc.shoppingcart.dto.CartResponse;
import com.mspoc.shoppingcart.dto.ProductDto;
import com.mspoc.shoppingcart.dto.ProductResponse;
import com.mspoc.shoppingcart.entity.CustomerCart;
import com.mspoc.shoppingcart.entity.ShoppingCart;
import com.mspoc.shoppingcart.repository.CustomerCartRepository;
import com.mspoc.shoppingcart.repository.ShoppingCartRepository;
import com.mspoc.shoppingcart.service.client.ProductFeignClient;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class for Shopping Cart service
 *
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

  @Autowired
  private ShoppingCartRepository cartRepository;

  @Autowired
  private CustomerCartRepository customerCartRepository;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CustomerCartService customerCartService;

  @Autowired
  private ProductFeignClient productFeignClient;

  /**
   * Function to add product to cart
   */
  @Override
  public void addProductToCart(CartRequest cartRequest) throws MsPlaformException {
    // check if customer id from customer table
    if (!customerService.customerExists(cartRequest.getCustomerId())) {
      throw new MsPlaformException(HttpStatus.NOT_FOUND.name(),
          "No customer exists with the customer id provided in the request");
    }

    try {
      // check if cart exists for the customer in the customercart table
      CustomerCart cartExists =
          customerCartRepository.findByCustomerId(cartRequest.getCustomerId());
      // assign a single cart to customer
      if (cartExists == null) {
        CustomerCart customerCart = new CustomerCart();
        customerCart.setCustomerId(cartRequest.getCustomerId());
        customerCartRepository.save(customerCart);
      }
      // check product id is valid or not by calling product microservice
      checkProductIdIsValid(cartRequest.getProductId());

      // fetch customer cart
      CustomerCart newCartCreated =
          customerCartRepository.findByCustomerId(cartRequest.getCustomerId());
      ShoppingCart cart = new ShoppingCart();
      cart.setCartId(newCartCreated.getCartId());
      cart.setProductId(cartRequest.getProductId());
      cart.setProductQuantity(cartRequest.getProductQuantity());
      cartRepository.save(cart);
    } catch (Exception e) {
      log.error("Error in add product to shopping cart method", e);
      throw new MsPlaformException(HttpStatus.INTERNAL_SERVER_ERROR.name(),
          "Could not add the product to shopping cart");
    }
  }

  /**
   * Function to get cart details for the customer
   */
  @Override
  public CartResponse getCartDetails(int customerId) throws MsPlaformException {
    int cartId = getCartIdForCustomer(customerId);
    try {
      List<ShoppingCart> shoppingCart = cartRepository.findByCartId(cartId);
      List<ProductDto> productDto = new ArrayList<>();
      List<Integer> productPrices = new ArrayList<>();
      CartResponse cartResponse = new CartResponse();
      shoppingCart.forEach(item -> {
        ProductDto productResponse = new ProductDto();
        int productPrice = 0;
        productResponse.setProductId(item.getProductId());
        productResponse.setProductQuantity(item.getProductQuantity());
        productDto.add(productResponse);
        // fetch the product price from product microservice to calculate the cart value
        try {
          productPrice = getProductPrice(item.getProductId()) * item.getProductQuantity();
          productPrices.add(productPrice);
        } catch (MsPlaformException e) {
          e.printStackTrace();
        }
      });
      Integer totalCartPrice =
          productPrices.stream().collect(Collectors.summingInt(Integer::intValue));
      cartResponse.setCartPrice(totalCartPrice);
      cartResponse.setProducts(productDto);
      return cartResponse;
    } catch (Exception e) {
      log.error("Error in get cart details for the customer function", e);
      throw new MsPlaformException(HttpStatus.INTERNAL_SERVER_ERROR.name(),
          "Could not fetch the cart details for the customer");
    }
  }

  /**
   * Function to delete a product from cart of the customer
   */
  @Override
  @Transactional(rollbackOn = Exception.class)
  public void deleteProductFromCart(int customerId, int productId) throws MsPlaformException {
    int cartId = getCartIdForCustomer(customerId);
    try {
      cartRepository.deleteByCartIdAndProductId(cartId, productId);
    } catch (Exception e) {
      log.error("Error in delete product from cart function", e);
      throw new MsPlaformException(HttpStatus.INTERNAL_SERVER_ERROR.name(),
          "Could not delete prodct from customer's cart");
    }
  }

  /**
   * Function to get cart id assigned to customer
   * 
   * @param customerId
   * @return int
   * @throws MsPlaformException
   */
  private int getCartIdForCustomer(int customerId) throws MsPlaformException {
    try {
      return customerCartService.getCartIdForCustomer(customerId);
    } catch (Exception e) {
      log.error("Error in get cart id for customer function", e);
      throw new MsPlaformException(HttpStatus.NOT_FOUND.name(),
          "An Error occurred while finding cart associated with customer");
    }
  }

  /**
   * Function to check product to be added to cart is valid or not by calling product microservice
   * 
   * @param productId
   * @return true/false
   * @throws MsPlaformException
   */
  private Boolean checkProductIdIsValid(int productId) throws MsPlaformException {
    ProductResponse product = productFeignClient.getProduct(productId);
    if (product != null && productId == product.getProductId()) {
      log.info("The product is valid");
      return true;
    } else {
      log.error("The product you want to add to cart is incorrect");
      throw new MsPlaformException(HttpStatus.NOT_FOUND.name(),
          "The product you want to add to cart is incorrect");
    }
  }

  /**
   * Function to get the price of the product from product microservice
   * 
   * @param productId
   * @return int
   * @throws MsPlaformException
   */
  private int getProductPrice(int productId) throws MsPlaformException {
    ProductResponse product = productFeignClient.getProduct(productId);
    if (product != null && productId == product.getProductId()) {
      log.info("The product is valid");
      return product.getProductPrice();
    } else {
      log.error("Could not fetch product price");
      throw new MsPlaformException(HttpStatus.NOT_FOUND.name(), "Could not fetch product price");
    }
  }
}
