package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.utils.InstantUtils;
import com.tokio.transfer.scheduler.domain.validation.Error;
import com.tokio.transfer.scheduler.domain.validation.ValidationHandler;
import com.tokio.transfer.scheduler.domain.validation.Validator;

public class TransferenceValidator extends Validator {

    public static final String ACCOUNT_PATTERN = "\\d{10}";
    private final Transference transference;

    public TransferenceValidator(final Transference aTransference, ValidationHandler aHandler) {
        super(aHandler);
        this.transference = aTransference;
    }

    @Override
    public void validate() {
        checkTransferDateContraints();
        checkAccountConstraints();
    }

    private void checkTransferDateContraints() {
        final var transferDate = this.transference.getTransferDate();
        if (transferDate == null) {
            this.validationHandler().append(new Error("'transferDate' should not be null"));
            return;
        }
        if (transferDate.getValue().isBefore(InstantUtils.now())) {
            this.validationHandler().append(new Error("'transferDate' should not be earlier than the current date"));
        }
    }

    private void checkAccountConstraints() {
        final var sourceAccount = this.transference.getSourceAccount();
        final var destinationAccount = this.transference.getDestinationAccount();
        if (sourceAccount == null) {
            this.validationHandler().append(new Error("'sourceAccount' should not be null"));
            return;
        }
        if (!sourceAccount.matches(ACCOUNT_PATTERN)) {
            this.validationHandler().append(new Error("'sourceAccount' should match the pattern 'XXXXXXXXXX'"));
            return;
        }
        if (destinationAccount == null) {
            this.validationHandler().append(new Error("'destinationAccount' should not be null"));
            return;
        }
        if (!destinationAccount.matches(ACCOUNT_PATTERN)) {
            this.validationHandler().append(new Error("'destinationAccount' should match the pattern 'XXXXXXXXXX'"));
        }
    }
}
