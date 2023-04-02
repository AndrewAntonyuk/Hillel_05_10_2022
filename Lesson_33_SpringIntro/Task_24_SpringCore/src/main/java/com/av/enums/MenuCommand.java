package com.av.enums;

public enum MenuCommand {
    EXIT("Exit from application."),
    ALLREP("Show all products in repository."),
    ALLCART("Show all products in cart."),
    ADD("Add one product to cart."),
    DELETE("Delete one product from cart.");

    private final String helpText;

    MenuCommand(String helpText) {
        this.helpText = helpText;
    }

    public String getHelpText() {
        return helpText;
    }
}
