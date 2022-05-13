package br.edu.unifei.inventory.application.db.usecase;

import br.edu.unifei.inventory.application.contract.GetAllProductUsecase;
import br.edu.unifei.inventory.application.db.repository.GetProductRepository;
import br.edu.unifei.inventory.domain.entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DbGetAllProductUsecase implements GetAllProductUsecase {
    private final GetProductRepository getProductRepository;

    @Override
    public List<Product> handle() {
        return this.handle(true);
    }

    @Override
    public List<Product> handle(Boolean includeInactive) {
        return getProductRepository.getAll(includeInactive);
    }
}
