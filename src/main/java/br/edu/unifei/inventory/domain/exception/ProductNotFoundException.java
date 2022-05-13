package br.edu.unifei.inventory.domain.exception;

import br.edu.unifei.common.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super("Product not found.");
    }
}
