package com.tokio.transfer.scheduler.domain.user;

import com.tokio.transfer.scheduler.domain.validation.Error;
import com.tokio.transfer.scheduler.domain.validation.ValidationHandler;
import com.tokio.transfer.scheduler.domain.validation.Validator;

public class UserValidator extends Validator {

    private final User user;

    public UserValidator(User user, ValidationHandler aHandler) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        checkUserLoginContraints();
    }

    private void checkUserLoginContraints() {
        final var login = this.user.getLogin();
        if (login == null || login.isEmpty()) {
            this.validationHandler().append(new Error("'login' should be informed"));
        }
    }
}
