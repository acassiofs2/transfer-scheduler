package com.tokio.transfer.scheduler.infrastructure.transference;

import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;
import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;
import com.tokio.transfer.scheduler.domain.transference.TransferenceID;
import com.tokio.transfer.scheduler.infrastructure.transference.persistence.TransferenceJpaEntity;
import com.tokio.transfer.scheduler.infrastructure.transference.persistence.TransferenceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.tokio.transfer.scheduler.infrastructure.utils.SpecificationUtils.eq;

@Component
public class TransferenceMySQLGateway implements TransferenceGateway {

    private final TransferenceRepository repository;

    public TransferenceMySQLGateway(final TransferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transference create(Transference aTransference) {
        return save(aTransference);
    }

    @Override
    public void deleteById(TransferenceID anId) {

    }

    @Override
    public Optional<Transference> findById(TransferenceID anId) {
        return Optional.empty();
    }

    @Override
    public Transference update(Transference aTransference) {
        return null;
    }

    @Override
    public Pagination<Transference> findAll(SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.getPage(),
                aQuery.getPerPage(),
                Sort.by(Sort.Direction.fromString(aQuery.getDirection()), aQuery.getSort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.getTerms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(TransferenceJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<TransferenceID> existsById(Iterable<TransferenceID> ids) {
        return List.of();
    }

    private Transference save(final Transference aTransference) {
        return this.repository.save(TransferenceJpaEntity.from(aTransference)).toAggregate();
    }

    private Specification<TransferenceJpaEntity> assembleSpecification(final String str) {
        return eq("user_id", str);
    }
}
