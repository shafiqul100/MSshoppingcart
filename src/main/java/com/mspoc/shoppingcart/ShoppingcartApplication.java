package com.mspoc.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class for shopping cart application
 *
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScans({
    @ComponentScan({"com.mspoc.shoppingcart.controller", "com.mspoc.msdevkit.exception.handler"})})
@EnableJpaRepositories("com.mspoc.shoppingcart.repository")
@EntityScan("com.mspoc.shoppingcart.entity")
public class ShoppingcartApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShoppingcartApplication.class, args);
  }

}
