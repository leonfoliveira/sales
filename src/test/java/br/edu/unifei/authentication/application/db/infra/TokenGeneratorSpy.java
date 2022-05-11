package br.edu.unifei.authentication.application.db.infra;

import com.github.javafaker.Faker;

import static org.mockito.Mockito.*;

public class TokenGeneratorSpy {
    public static TokenGenerator get() {
        TokenGenerator tokenGenerator = spy(TokenGenerator.class);
        Faker faker = new Faker();

        when(tokenGenerator.generate(any()))
                .thenReturn(faker.lorem().characters());

        return tokenGenerator;
    }
}
