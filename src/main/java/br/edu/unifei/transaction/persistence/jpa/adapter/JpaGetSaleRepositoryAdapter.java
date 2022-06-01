package br.edu.unifei.transaction.persistence.jpa.adapter;

import br.edu.unifei.transaction.application.db.repository.GetSaleRepository;
import br.edu.unifei.transaction.domain.entity.Sale;
import br.edu.unifei.transaction.persistence.jpa.entity.JpaSale;
import br.edu.unifei.transaction.persistence.jpa.repository.JpaSaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaGetSaleRepositoryAdapter implements GetSaleRepository {
    private final JpaSaleRepository saleRepository;

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll()
                .stream()
                .map(JpaSale::toDomainEntity)
                .toList();
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        return saleRepository.findById(id)
                .map(JpaSale::toDomainEntity);
    }
}
