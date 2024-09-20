package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.application.UseCase;
import com.tokio.transfer.scheduler.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateTransferenceUseCase
        extends UseCase<CreateTransferenceCommand, Either<Notification, CreateTransferenceOutput>> {
}
