package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.utils.DateUtils;
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
        checkTaxConstraints();
    }

    private void checkTransferDateContraints() {
        final var transferDate = this.transference.getTransferDate();
        if (transferDate == null) {
            this.validationHandler().append(new Error("A data de transferência deve ser informada"));
            return;
        }
        if (transferDate.getValue().isBefore(DateUtils.now())) {
            this.validationHandler().append(new Error("A data de transferência deve ser maior ou igual à data atual"));
        }
    }

    private void checkAccountConstraints() {
        final var sourceAccount = this.transference.getSourceAccount();
        final var destinationAccount = this.transference.getDestinationAccount();
        if (sourceAccount == null) {
            this.validationHandler().append(new Error("A Conta de Origem deve ser informada"));
            return;
        }
        if (!sourceAccount.matches(ACCOUNT_PATTERN)) {
            this.validationHandler().append(new Error("A Conta de Origem deve estar no padrão 'XXXXXXXXXX'"));
            return;
        }
        if (destinationAccount == null) {
            this.validationHandler().append(new Error("A Conta de Destino deve ser informada"));
            return;
        }
        if (!destinationAccount.matches(ACCOUNT_PATTERN)) {
            this.validationHandler().append(new Error("A Conta de Destino deve estar no padrão 'XXXXXXXXXX'"));
        }
    }

    private void checkTaxConstraints() {
        final var tax = this.transference.calculateTax();
        if (tax == null) {
            this.validationHandler().append(new Error("Nenhuma taxa aplicável para a data escolhida"));
        }
    }
}
