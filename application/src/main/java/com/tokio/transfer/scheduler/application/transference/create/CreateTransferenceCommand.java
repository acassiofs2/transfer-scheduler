package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;

public class CreateTransferenceCommand {
    private String sourceAccount;
    private String destinationAccount;
    private Decimal amount;
    private Date transferDate;
    private boolean active;

    private CreateTransferenceCommand(String sourceAccount, String destinationAccount, Decimal amount, Date transferDate, boolean active) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transferDate = transferDate;
        this.active = active;
    }

    public static CreateTransferenceCommand with(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Decimal aAmount,
            final Date aTransferDate,
            final boolean isActive
    ) {
        return new CreateTransferenceCommand(aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive);
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
}
