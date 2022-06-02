package br.edu.unifei.transaction.presentation.springweb.request;

import br.edu.unifei.transaction.application.dto.CreateSaleItemDTOMock;
import com.github.javafaker.Faker;

import java.util.List;

public abstract class CreateSaleRequestMock {
    public static CreateSaleRequest get() {
        return CreateSaleRequest.builder()
                .items(List.of(CreateSaleItemDTOMock.get()))
                .build();
    }
}