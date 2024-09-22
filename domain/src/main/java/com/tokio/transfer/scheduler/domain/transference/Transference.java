package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.AggregateRoot;
import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.user.UserID;
import com.tokio.transfer.scheduler.domain.utils.DecimalUtils;
import com.tokio.transfer.scheduler.domain.utils.DateUtils;
import com.tokio.transfer.scheduler.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class Transference extends AggregateRoot<TransferenceID> implements Cloneable {

    private UserID userID;
    private String sourceAccount;
    private String destinationAccount;
    private Decimal amount;
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
        this.transferDate = Date.of(aTransferDate);
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    private Transference(
            final TransferenceID anID,
            final UserID userID,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anID);
        this.userID = userID;
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = Decimal.of(aAmount, 2, "R$");
        this.transferDate = Date.of(aTransferDate);
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    private Transference(
            final TransferenceID anID,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anID);
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = Decimal.of(aAmount, 2, "R$");
        this.transferDate = Date.of(aTransferDate);
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    private Transference(
            final TransferenceID anID,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Decimal aAmount,
            final Date aTransferDate,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anID);
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = aAmount;
        this.transferDate = aTransferDate;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Transference newTransference(
            final UserID userID,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
            final boolean isActive
    ) {
        final var id = TransferenceID.unique();
        final var now = DateUtils.nowInstant();
        final var deletedAt = isActive ? null : now;
        return new Transference(
                id, userID, aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive, now, now, deletedAt
        );
    }

    public static Transference newTransference(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final String aTransferDate,
            final boolean isActive
    ) {
        final var id = TransferenceID.unique();
        final var now = DateUtils.nowInstant();
        final var deletedAt = isActive ? null : now;
        return new Transference(
                id, aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive, now, now, deletedAt
        );
    }

    public static Transference with(
            final TransferenceID id,
            final UserID userId,
            final String sourceAccount,
            final String destinationAccount,
            final Double amount,
            final LocalDate transferDate,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Transference(
                id,
                userId,
                sourceAccount,
                destinationAccount,
                amount,
                transferDate,
                active,
                createdAt,
                updatedAt,
                deletedAt
        );
    };

    public static Transference newTransference(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
            final boolean isActive
    ) {
        final var id = TransferenceID.unique();
        final var now = DateUtils.nowInstant();
        final var deletedAt = isActive ? null : now;
        return new Transference(
                id, aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive, now, now, deletedAt
        );
    }

    public static Transference newTransference(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Decimal aAmount,
            final Date aTransferDate,
            final boolean isActive
    ) {
        final var id = TransferenceID.unique();
        final var now = DateUtils.nowInstant();
        final var deletedAt = isActive ? null : now;
        return new Transference(
                id, aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive, now, now, deletedAt
        );
    }

    public UserID getUserID() {
        return userID;
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
        var taxPerCentage = calculateTax();
        if (taxPerCentage == null) taxPerCentage = 0.0;
        var amountValue = getAmount().getValue();
        return DecimalUtils.calculatePercent(amountValue, taxPerCentage);
    }

    public Double calculateTax() {
        Double aTax = null;
        if (getTransferDate() != null && getTransferDate().getValue() != null) {
            final var daysCount = DAYS.between(DateUtils.now(), getTransferDate().getValue());
            if (daysCount == 0) aTax = 2.5;
            else if (daysCount >= 1 && daysCount <= 10) aTax = 0.0;
            else if (daysCount >= 11 && daysCount <= 20) aTax = 8.2;
            else if (daysCount >= 21 && daysCount <= 30) aTax = 6.9;
            else if (daysCount >= 31 && daysCount <= 40) aTax = 4.7;
            else if (daysCount >= 41 && daysCount <= 50) aTax = 1.7;
        }
        return aTax;
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
        new TransferenceValidator(this, handler).validate();
    }
}
