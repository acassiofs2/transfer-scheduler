package com.tokio.transfer.scheduler.infrastructure.api.controllers;

import com.tokio.transfer.scheduler.application.transference.create.CreateTransferenceCommand;
import com.tokio.transfer.scheduler.application.transference.create.CreateTransferenceOutput;
import com.tokio.transfer.scheduler.application.transference.create.CreateTransferenceUseCase;
import com.tokio.transfer.scheduler.application.transference.retrieve.list.ListTransferencesUseCase;
import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;
import com.tokio.transfer.scheduler.domain.validation.handler.Notification;
import com.tokio.transfer.scheduler.infrastructure.api.TransferenceAPI;
import com.tokio.transfer.scheduler.infrastructure.transference.models.CreateTransferenceRequest;
import com.tokio.transfer.scheduler.infrastructure.transference.models.TransferenceListResponse;
import com.tokio.transfer.scheduler.infrastructure.transference.presenters.TransferenceApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class TransferenceController implements TransferenceAPI {

    private final CreateTransferenceUseCase createTransferenceUseCase;
    private final ListTransferencesUseCase listTransferencesUseCase;

    public TransferenceController(
            final CreateTransferenceUseCase createTransferenceUseCase,
            final ListTransferencesUseCase listTransferencesUseCase
    ) {
        this.createTransferenceUseCase = Objects.requireNonNull(createTransferenceUseCase);
        this.listTransferencesUseCase = Objects.requireNonNull(listTransferencesUseCase);
    }

    @Override
    public ResponseEntity<?> createTransference(final CreateTransferenceRequest input) {
        final var aCommand = CreateTransferenceCommand.with(
                input.getUserId(),
                input.getSourceAccount(),
                input.getDestinationAccount(),
                input.getAmount().doubleValue(),
                input.getTransferDate(),
                input.getActive()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateTransferenceOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/transferences/" + output.getId())).body(output);

        return this.createTransferenceUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<TransferenceListResponse> listTransferences(
            String search,
            int page,
            int perPage,
            String sort,
            String direction
    ) {
        return listTransferencesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(TransferenceApiPresenter::present);
    }
}
