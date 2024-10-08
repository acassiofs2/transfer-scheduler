package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.user.UserID;

import java.time.LocalDate;

public class CreateTransferenceCommand {
    private String userId;
    private String sourceAccount;
    private String destinationAccount;
    private Decimal amount;
    private Date transferDate;
    private boolean active;

    private CreateTransferenceCommand(String sourceAccount, String destinationAccount, Double amount, String transferDate, boolean active) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = Decimal.of(amount, 2);
        this.transferDate = Date.of(transferDate);
        this.active = active;
    }

    private CreateTransferenceCommand(String sourceAccount, String destinationAccount, Double amount, Date transferDate, boolean active) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = Decimal.of(amount, 2);
        this.transferDate = transferDate;
        this.active = active;
    }

    private CreateTransferenceCommand(String sourceAccount, String destinationAccount, Double amount, LocalDate transferDate, boolean active) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = Decimal.of(amount, 2);
        this.transferDate = Date.of(transferDate);
        this.active = active;
    }

    private CreateTransferenceCommand(String userId, String sourceAccount, String destinationAccount, Double amount, LocalDate transferDate, boolean active) {
        this.userId = userId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = Decimal.of(amount, 2);
        this.transferDate = Date.of(transferDate);
        this.active = active;
    }

    public static CreateTransferenceCommand with(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final String aTransferDate,
            final boolean isActive
    ) {
        return new CreateTransferenceCommand(aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive);
    }

    public static CreateTransferenceCommand with(
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final Date aTransferDate,
            final boolean isActive
    ) {
        return new CreateTransferenceCommand(aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive);
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

    public static CreateTransferenceCommand with(
            final String userId,
            final String aSourceAccount,
            final String aDestinationAccount,
            final Double aAmount,
            final LocalDate aTransferDate,
            final boolean isActive
    ) {
        return new CreateTransferenceCommand(userId, aSourceAccount, aDestinationAccount, aAmount, aTransferDate, isActive);
    }

    public String getUserId() {
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

    public Date getTransferDate() {
        return transferDate;
    }

    public boolean isActive() {
        return active;
    }
}
