package com.tokio.transfer.scheduler.application.transference.retrieve.list;

import com.tokio.transfer.scheduler.application.UseCase;
import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;

public abstract class ListTransferencesUseCase
        extends UseCase<SearchQuery, Pagination<TransferenceListOutput>> {
}
