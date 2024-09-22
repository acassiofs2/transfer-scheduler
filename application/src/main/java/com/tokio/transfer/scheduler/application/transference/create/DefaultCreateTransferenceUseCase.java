package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;
import com.tokio.transfer.scheduler.domain.user.UserID;
import com.tokio.transfer.scheduler.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateTransferenceUseCase extends CreateTransferenceUseCase {

    private final TransferenceGateway transferenceGateway;

    public DefaultCreateTransferenceUseCase(final TransferenceGateway transferenceGateway) {
        this.transferenceGateway = Objects.requireNonNull(transferenceGateway);
    }

    @Override
    public Either<Notification, CreateTransferenceOutput> execute(CreateTransferenceCommand aCommand) {
        final var anUserId = UserID.from(aCommand.getUserId());
        final var aSourceAccount = aCommand.getSourceAccount();
        final var aDestinationAccount = aCommand.getDestinationAccount();
        final var aAmount = aCommand.getAmount();
        final var aTransferDate = aCommand.getTransferDate();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aTransference =
                Transference.newTransference(anUserId, aSourceAccount, aDestinationAccount, aAmount.getValue().doubleValue(), aTransferDate.getValue(), isActive);
        aTransference.validate(notification);

        return notification.hasError() ? Left(notification) : create(aTransference);
    }

    private Either<Notification, CreateTransferenceOutput> create(final Transference aTransference) {
        return Try(() -> this.transferenceGateway.create(aTransference))
                .toEither()
                .bimap(Notification::create, CreateTransferenceOutput::from);
    }
}
