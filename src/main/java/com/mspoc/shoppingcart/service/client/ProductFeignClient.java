package com.mspoc.shoppingcart.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mspoc.shoppingcart.dto.ProductResponse;

/**
 * Feign client for communicating with product microservice
 *
 */
@FeignClient("product")
public interface ProductFeignClient {

  @RequestMapping(method = RequestMethod.GET, value = "product")
  ProductResponse getProduct(@RequestParam int productId);
}
