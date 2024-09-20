package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface TransferenceGateway {

    Transference create(Transference aTransference);

    void deleteById(TransferenceID anId);

    Optional<Transference> findById(TransferenceID anId);

    Transference update(Transference aTransference);

    Pagination<Transference> findALl(SearchQuery aQuery);

    List<TransferenceID> existsById(Iterable<TransferenceID> ids);
}
