package br.edu.unifei.inventory.presentation.request;

import com.github.javafaker.Faker;

public abstract class CreateProductRequestMock {
    public static CreateProductRequest get() {
        Faker fake = new Faker();
        return CreateProductRequest.builder
    }

}
