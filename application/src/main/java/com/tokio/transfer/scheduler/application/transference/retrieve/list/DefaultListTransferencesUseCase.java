package com.tokio.transfer.scheduler.application.transference.retrieve.list;

import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;

import java.util.Objects;

public class DefaultListTransferencesUseCase extends ListTransferencesUseCase {

    private final TransferenceGateway transferenceGateway;

    public DefaultListTransferencesUseCase(TransferenceGateway transferenceGateway) {
        this.transferenceGateway = Objects.requireNonNull(transferenceGateway);
    }

    @Override
    public Pagination<TransferenceListOutput> execute(SearchQuery aQuery) {
        return this.transferenceGateway.findAll(aQuery)
                .map(TransferenceListOutput::from);
    }
}
