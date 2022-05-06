package br.edu.unifei.inventory.application.contract;

import br.edu.unifei.inventory.application.dto.CreateProductDTO;
import br.edu.unifei.inventory.domain.entity.Product;
import br.edu.unifei.inventory.domain.exception.BarCodeInUseException;

public interface CreateProductUsecase {
    Product handle(CreateProductDTO dto) throws BarCodeInUseException;
}
