package com.tokio.transfer.scheduler.domain.exceptions;

import com.tokio.transfer.scheduler.domain.AggregateRoot;
import com.tokio.transfer.scheduler.domain.Identifier;
import com.tokio.transfer.scheduler.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = String.format("%s with ID %s was not found",
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(final Error error) {
        return new NotFoundException(error.getMessage(), List.of(error));
    }
}
