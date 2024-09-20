package com.tokio.transfer.scheduler.application.transference.create;

import java.time.LocalDate;

public class CreateTransferenceCommand {
    private String sourceAccount;
    private String destinationAccount;
    private Double amount;
    private LocalDate transferDate;
    private boolean active;

    private CreateTransferenceCommand(String sourceAccount, String destinationAccount, Double amount, LocalDate transferDate, boolean active) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transferDate = transferDate;
        this.active = active;
    }

    public static CreateTransferenceCommand with(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
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

    public Double getAmount() {
        return amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public boolean isActive() {
        return active;
    }
}
