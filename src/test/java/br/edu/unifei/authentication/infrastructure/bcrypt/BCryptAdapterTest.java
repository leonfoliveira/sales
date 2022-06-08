package br.edu.unifei.authentication.infrastructure.bcrypt;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class BCryptAdapterTest {
    BCryptAdapter sut;
    Faker faker = new Faker();

    @BeforeEach
    void setup() {
        sut = new BCryptAdapter();
    }

    @Test
    void generateShouldReturnAHashedValue() {
        String value = faker.lorem().word();
        String hash = sut.generate(value);
        assertNotNull(hash);
    }

    @Test
    void compareShouldReturnFalseIfHashIsNotEqualsValue() {
        String value = faker.lorem().word();
        String hash = BCrypt.hashpw(value + " ", BCrypt.gensalt());
        Boolean result = sut.compare(value, hash);
        assertFalse(result);
    }

    @Test
    void compareShouldReturnTrueIfHashIsEqualsValue() {
        String value = faker.lorem().word();
        String hash = sut.generate(value);
        Boolean result = sut.compare(value, hash);
        assertTrue(result);
    }
}
