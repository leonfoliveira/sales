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
    private CreateProductUsecase createProductUsecase() {
        return new DbCreateProductUsecase(jpaGetProductRepositoryAdapter,
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    private FindProductByBarCodeUsecase findProductByBarCodeUsecase() {
        return new DbFindProductByBarCodeUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    private FindProductByIdUsecase findProductByIdUsecase() {
        return new DbFindProductByIdUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    private GetAllProductUsecase getAllProductUsecase() {
        return new DbGetAllProductUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    private ToggleActivenessProductUsecase toggleActivenessProductUsecase() {
        return new DbToggleActivenessProductUsecase(findProductByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }

    @Bean
    private UpdateProductUsecase updateProductUsecase() {
        return new DbUpdateProductUsecase(jpaGetProductRepositoryAdapter,
                findProductByIdUsecase(),
                jpaSaveUserRepositoryAdapter);
    }
}
