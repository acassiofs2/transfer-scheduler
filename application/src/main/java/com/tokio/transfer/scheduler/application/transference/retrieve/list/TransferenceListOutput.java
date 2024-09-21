package com.tokio.transfer.scheduler.application.transference.retrieve.list;

import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceID;

import java.time.Instant;

public class TransferenceListOutput {

    private final TransferenceID id;
    private final String sourceAccount;
    private final String destinationAccount;
    private final Decimal amount;
    private final Date transferDate;
    private final boolean active;
    private final Instant createdAt;

    private TransferenceListOutput(
            TransferenceID id,
            String aSourceAccount,
            String aDestinationAccount,
            Decimal aAmount,
            Date aTransferDate,
            boolean isActive,
            Instant aCreatedAt) {
        this.id = id;
        this.sourceAccount = aSourceAccount;
        this.destinationAccount = aDestinationAccount;
        this.amount = aAmount;
        this.transferDate = aTransferDate;
        this.active = isActive;
        this.createdAt = aCreatedAt;
    }

    public static TransferenceListOutput from(final Transference aTransference) {
        return new TransferenceListOutput(
                aTransference.getId(),
                aTransference.getSourceAccount(),
                aTransference.getDestinationAccount(),
                aTransference.getAmount(),
                aTransference.getTransferDate(),
                aTransference.isActive(),
                aTransference.getCreatedAt()
        );
    }

    public TransferenceID getId() {
        return id;
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
