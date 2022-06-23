package br.edu.unifei.inventory.presentation.springweb.request;

import br.edu.unifei.inventory.domain.entity.UnitType;
import com.github.javafaker.Faker;

public abstract class CreateProductRequestMock {
    public static CreateProductRequest get() {
        Faker faker = new Faker();
        return CreateProductRequest.builder()
                .title(faker.book().title())
                .barCode(faker.number().digits(13))
                .unitPrice(faker.number().randomDouble(4, 0, 1000))
                .unitType(faker.options().option(UnitType.class).toString())
                .build();

    }

}
