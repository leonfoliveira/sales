package br.edu.unifei.authentication.infrastructure.jwt;

import br.edu.unifei.authentication.application.model.AuthorizationMock;
import br.edu.unifei.authentication.application.model.AuthorizationPayload;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtAdapterTest {
    JwtAdapter sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        sut = new JwtAdapter(faker.lorem().word(), faker.random().nextInt(1000, 10000));
    }

    @Test
    void generateShouldReturnAJWTToken() {
        AuthorizationPayload payload = AuthorizationMock.get().payload();
        Map<String, Object> claims = new HashMap<>();
        claims.put("payload", payload);
        String token = sut.generate(claims);
        assertNotNull(token);
    }
}