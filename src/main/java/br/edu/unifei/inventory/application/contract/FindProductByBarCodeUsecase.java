package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.ProductNotFoundException;

public interface FindProductByBarCodeUsecase {
    Product handle(String barCode) throws ProductNotFoundException;
}
