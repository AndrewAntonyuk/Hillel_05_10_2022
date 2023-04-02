package com.av.utils;

import com.av.entity.Cart;
import com.av.enums.MenuCommand;
import com.av.repository.ProductRepository;
import com.av.exception.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
        MenuCommand command;

        while (typed != 0) {
            typed = scanner.nextInt();

            try {
                command = MenuCommand.values()[typed];

                switch (command) {
                    case EXIT -> System.out.println("Good luck!");
                    case ALLREP -> {
                        showAllProducts();
                    }
                    case ALLCART -> {
                        showAllCartProducts();
                    }
                    case ADD -> {
                        addProduct();
                    }
                    case DELETE -> {
                        deleteProduct();
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Undefined input " + typed);
                showAdvice();
            } catch (RuntimeException e) {
                throw new RuntimeException();
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
        Arrays.stream(MenuCommand.values())
                .forEach(command ->
                        System.out.println(command.ordinal() + " - " + command.getHelpText()));

        System.out.println("Please, enter your choice:");
        System.out.println("=================================");
    }
}
