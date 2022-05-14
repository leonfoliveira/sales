package br.edu.unifei.common.validator;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UUIDValidatorTest {
    UUIDValidator sut = new UUIDValidator();
    ConstraintValidatorContext context = new ConstraintValidatorContextMock();
    Faker faker = new Faker();

    @Test
    void shouldReturnTrueForAValidUUID() {
        UUID uuid = UUID.randomUUID();
        boolean result = sut.isValid(uuid.toString(), context);
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForAnInvalidUUID() {
        boolean result = sut.isValid(faker.lorem().word(), context);
        assertFalse(result);
    }
}
