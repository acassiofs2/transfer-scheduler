package com.tokio.transfer.scheduler.application.transference.retrieve.list;

import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceID;
import com.tokio.transfer.scheduler.domain.user.UserID;

import java.time.Instant;

public class TransferenceListOutput {

    private final TransferenceID id;
    private final UserID userId;
    private final String sourceAccount;
    private final String destinationAccount;
    private final Decimal amount;
    private final Decimal tax;
    private final Date transferDate;
    private final boolean active;
    private final Instant createdAt;

    private TransferenceListOutput(
            final TransferenceID id,
            final UserID userId,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Decimal aAmount,
            final Decimal aTax,
            final Date aTransferDate,
            final boolean isActive,
            final Instant aCreatedAt) {
        this.id = id;
        this.userId = userId;
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = aAmount;
        this.tax = aTax;
        this.transferDate = aTransferDate;
        this.active = isActive;
        this.createdAt = aCreatedAt;
    }

    public static TransferenceListOutput from(final Transference aTransference) {
        return new TransferenceListOutput(
                aTransference.getId(),
                aTransference.getUserID(),
                aTransference.getSourceAccount(),
                aTransference.getDestinationAccount(),
                aTransference.getAmount(),
                aTransference.getTax(),
                aTransference.getTransferDate(),
                aTransference.isActive(),
                aTransference.getCreatedAt()
        );
    }

    public TransferenceID getId() {
        return id;
    }

    public UserID getUserId() {
        return userId;
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
}
