package br.edu.unifei.authentication.presentation.springweb.request;

import com.github.javafaker.Faker;

public abstract class LoginUserRequestMock {
    public static LoginUserRequest get() {
        Faker faker = new Faker();
        return LoginUserRequest.builder()
                .login(faker.name().firstName())
                .password(faker.internet().password())
                .build();
    }
}
