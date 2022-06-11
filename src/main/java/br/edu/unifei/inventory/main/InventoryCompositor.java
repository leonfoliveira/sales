package br.edu.unifei.inventory.main;

import br.edu.unifei.inventory.application.contract.*;
import br.edu.unifei.inventory.application.db.usecase.*;
import br.edu.unifei.inventory.persistence.jpa.adapter.JpaGetProductRepositoryAdapter;
import br.edu.unifei.inventory.persistence.jpa.adapter.JpaSaveProductRepositoryAdapter;
import br.edu.unifei.inventory.persistence.jpa.repository.JpaProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryCompositor {
    private final JpaGetProductRepositoryAdapter jpaGetProductRepositoryAdapter;
    private final JpaSaveProductRepositoryAdapter jpaSaveUserRepositoryAdapter;

    public InventoryCompositor(JpaProductRepository jpaProductRepository) {
        jpaGetProductRepositoryAdapter = new JpaGetProductRepositoryAdapter(jpaProductRepository);
        jpaSaveUserRepositoryAdapter = new JpaSaveProductRepositoryAdapter(jpaProductRepository);
    }

    @Bean
    public CreateProductUsecase createProductUsecase() {
        return new DbCreateProductUsecase(jpaGetProductRepositoryAdapter,
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    public FindProductByBarCodeUsecase findProductByBarCodeUsecase() {
        return new DbFindProductByBarCodeUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    public FindProductByIdUsecase findProductByIdUsecase() {
        return new DbFindProductByIdUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    public GetAllProductUsecase getAllProductUsecase() {
        return new DbGetAllProductUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    public ToggleActivenessProductUsecase toggleActivenessProductUsecase() {
        return new DbToggleActivenessProductUsecase(findProductByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    public UpdateProductUsecase updateProductUsecase() {
        return new DbUpdateProductUsecase(jpaGetProductRepositoryAdapter,
                findProductByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }
}
