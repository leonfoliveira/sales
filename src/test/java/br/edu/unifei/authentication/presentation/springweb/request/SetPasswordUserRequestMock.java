package br.edu.unifei.authentication.presentation.springweb.request;

import com.github.javafaker.Faker;

public abstract class SetPasswordUserRequestMock {
    public static SetPasswordUserRequest get() {
        Faker faker = new Faker();
        return SetPasswordUserRequest.builder()
                .password(faker.internet().password())
                .build();
    }
}
