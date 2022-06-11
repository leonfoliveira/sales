package br.edu.unifei.transaction.main;

import br.edu.unifei.inventory.application.contract.FindProductByIdUsecase;
import br.edu.unifei.inventory.application.db.usecase.DbFindProductByIdUsecase;
import br.edu.unifei.inventory.persistence.jpa.adapter.JpaGetProductRepositoryAdapter;
import br.edu.unifei.inventory.persistence.jpa.repository.JpaProductRepository;
import br.edu.unifei.transaction.application.contract.*;
import br.edu.unifei.transaction.application.db.usecase.*;
import br.edu.unifei.transaction.persistence.jpa.adapter.JpaGetSaleRepositoryAdapter;
import br.edu.unifei.transaction.persistence.jpa.adapter.JpaSaveSaleRepositoryAdapter;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionCompositor {
    private final JpaGetSaleRepositoryAdapter jpaGetSaleRepositoryAdapter;
    private final JpaSaveSaleRepositoryAdapter jpaSaveSaleRepositoryAdapter;
    private final JpaGetProductRepositoryAdapter jpaGetProductRepositoryAdapter;

    public TransactionCompositor (JpaSaleRepository jpaSaleRepository, JpaProductRepository jpaProductRepository) {
        jpaGetSaleRepositoryAdapter = new JpaGetSaleRepositoryAdapter(jpaSaleRepository);
        jpaSaveSaleRepositoryAdapter = new JpaSaveSaleRepositoryAdapter(jpaSaleRepository);
        jpaGetProductRepositoryAdapter = new JpaGetProductRepositoryAdapter(jpaProductRepository);
    }

    @Bean
    public FindProductByIdUsecase findProductByIdUsecase () {
        return new DbFindProductByIdUsecase(jpaGetProductRepositoryAdapter);
    }

    @Bean
    public CreateSaleUsecase createSaleUsecase () {
        return new DbCreateSaleUseCase(findProductByIdUsecase(), jpaSaveSaleRepositoryAdapter);
    }

    @Bean
    public DeleteSaleUsecase deleteSaleUsecase () {
        return new DbDeleteSaleUseCase(jpaSaveSaleRepositoryAdapter, jpaGetSaleRepositoryAdapter);
    }

    @Bean
    public FindSaleByIdUsecase findSaleByIdUsecase () {
        return new DbFindSaleByIdUseCase(jpaGetSaleRepositoryAdapter);
    }

    @Bean
    public GetAllSaleUsecase getAllSaleUsecase () {
        return new DbGetAllSaleUseCase(jpaGetSaleRepositoryAdapter);
    }

}