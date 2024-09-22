package com.tokio.transfer.scheduler.infrastructure.transference.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenceRepository extends JpaRepository<TransferenceJpaEntity, String> {

    Page<TransferenceJpaEntity> findAll(Specification<TransferenceJpaEntity> whereClause, Pageable page);
}
