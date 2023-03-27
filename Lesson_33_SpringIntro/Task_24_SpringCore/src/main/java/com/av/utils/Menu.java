package com.av.utils;

import com.av.entity.Cart;
import com.av.repository.ProductRepository;
import com.av.exception.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Scope(scopeName = "prototype")
public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private Cart cart;
    @Autowired
    private ProductRepository productRepository;

    public void run() {
        showAdvice();
        readUserInput();
    }

    private void readUserInput() {
        int typed = -1;

        while (typed != 0) {
            typed = scanner.nextInt();

            switch (typed) {
                case 0 -> System.out.println("Good luck!");
                case 1 -> {
                    showAllProducts();
                }
                case 2 -> {
                    showAllCartProducts();
                }
                case 3 -> {
                    addProduct();
                }
                case 4 -> {
                    deleteProduct();
                }
                default -> {
                    System.out.println("Undefined input " + typed);
                    showAdvice();
                }
            }
        }
    }

    private void showAllProducts() {
        System.out.println("All products in repository:");

        productRepository.getProducts().forEach(System.out::println);

        System.out.println("=================================");
    }

    private void showAllCartProducts() {
        System.out.println("All products in cart:");

        System.out.println(cart.showProducts());

        System.out.println("=================================");
    }

    private void addProduct() {
        System.out.println("Enter ID of product for adding to cart:");

        Long typedID = scanner.nextLong();

        try {
            cart.saveProduct(typedID);
        } catch (NoSuchProductException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }

        System.out.println("=================================");
    }

    private void deleteProduct() {
        System.out.println("Enter ID of product for deleting from cart:");

        Long typedID = scanner.nextLong();

        try {
            cart.removeProduct(typedID);
        } catch (NoSuchProductException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }

        System.out.println("=================================");
    }

    private void showAdvice() {
        System.out.println("1 - Show all products in repository");
        System.out.println("2 - Show all products in cart");
        System.out.println("3 - Add product to cart");
        System.out.println("4 - Delete product from cart");
        System.out.println("0 - Exit");
        System.out.println("Please, enter your choice:");
        System.out.println("=================================");
    }
}
