package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.AggregateRoot;
import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.utils.InstantUtils;
import com.tokio.transfer.scheduler.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Transference extends AggregateRoot<TransferenceID> implements Cloneable {

    private String sourceAccount;
    private String destinationAccount;
    private Decimal amount;
    private Decimal tax;
    private Date transferDate;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Transference(
            final TransferenceID anID,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final Double aTax,
            final String aTransferDate,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anID);
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = Decimal.of(aAmount, 2, "R$");
        this.tax = Decimal.of(aTax, 2);
        this.transferDate = Objects.requireNonNull(Date.of(aTransferDate), "'transferDate' should not be null");
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Transference newTransference(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final Double aTax,
            final String aTransferDate,
            final boolean isActive
    ) {
        final var id = TransferenceID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Transference(
                id, aSourceAccount, aDestinationAccount, aAmount, aTax, aTransferDate, isActive, now, now, deletedAt
        );
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public Decimal getAmount() {
        return amount;
    }

    public Decimal getTax() {
        return tax;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Transference clone() {
        try {
            return (Transference) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
