package com.bmworks.usecases;

public class RegisterProduct {

    private ProductEventListener productEventListener;

    public RegisterProduct(ProductEventListener productEventListener) {
        this.productEventListener = productEventListener;
    }

    public String execute(Product product) {
        productEventListener.onProductCreated(product);
        return product.getName();
    }

}
