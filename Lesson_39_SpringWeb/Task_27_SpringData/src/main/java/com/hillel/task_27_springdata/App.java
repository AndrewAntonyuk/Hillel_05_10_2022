package com.hillel.task_27_springdata;

import com.hillel.task_27_springdata.entity.Cart;
import com.hillel.task_27_springdata.entity.Product;
import com.hillel.task_27_springdata.repository.CartRepository;
import com.hillel.task_27_springdata.repository.CartRepositoryImpl;
import com.hillel.task_27_springdata.repository.ProductRepository;
import com.hillel.task_27_springdata.repository.ProductRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner getRunner(ApplicationContext context) {
        return (argument) -> {
            ProductRepository productRepository = context.getBean(ProductRepositoryImpl.class);
            CartRepository cartRepository = context.getBean(CartRepositoryImpl.class);

            System.out.println(System.lineSeparator() + "============SHOW RESULTS FOR PRODUCT'S REPO=================");
            System.out.println(System.lineSeparator() + "------------Create new product--------------");
            Long cake = 0L;
            //cake = productRepository.create(new Product(1L, "Cake", new BigDecimal("24.89")));
            //System.out.println("Was added product with id " + cake + ": " + productRepository.getById(cake));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Delete exist product--------------");
            //cake = 7L;
            //System.out.println("Was deleted product with id " + cake + ": " + productRepository.deleteById(cake));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Get one product by id--------------");
            cake = 8L;
            System.out.println("Product with id " + cake + ": " + productRepository.getById(cake));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Get all products--------------");
            System.out.println("All products: " + productRepository.getAll());

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "============SHOW RESULTS FOR CART'S REPO=================");
            System.out.println(System.lineSeparator() + "------------Create new cart--------------");
            Long cart = 0L;
//			cart = cartRepository.create(new Cart());
//			System.out.println("Was added cart with id " + cart + ": " + cartRepository.getById(cart));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Delete exist cart--------------");
//			cart = 5L;
//			System.out.println("Was deleted cart with id " + cart + ": " + cartRepository.deleteById(cart));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Get one cart by id--------------");
            cart = 17L;
            System.out.println("Cart with id " + cart + ": " + cartRepository.getById(cart));

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());
        };
    }
}
