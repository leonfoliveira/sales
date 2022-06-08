package br.edu.unifei.authentication.application.db.infrastructure;

import com.github.javafaker.Faker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class HashGeneratorSpy {
    public static HashGenerator get() {
        HashGenerator hashGeneratorSpy = spy(HashGenerator.class);
        Faker faker = new Faker();

        when(hashGeneratorSpy.generate(any()))
                .thenReturn(faker.lorem().characters());

        return hashGeneratorSpy;
    }
}
