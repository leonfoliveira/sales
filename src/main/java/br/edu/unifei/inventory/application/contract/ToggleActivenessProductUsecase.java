package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;

import java.util.UUID;

public interface ToggleActivenessProductUsecase {
    void handle(UUID productId) throws ProductNotFoundException;
}
