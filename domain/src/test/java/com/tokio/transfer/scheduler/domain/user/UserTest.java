package com.tokio.transfer.scheduler.domain.user;

import com.tokio.transfer.scheduler.domain.exceptions.DomainException;
import com.tokio.transfer.scheduler.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void givenAValidParams_whenCallNewUser_thenInstantiateAUser() {
        final var expectedName = "João Silva";
        final var expectedLogin = "joao";
        final var expectedIsActive = true;

        final var actualUser =
                User.newUser(expectedName, expectedLogin, expectedIsActive);

        Assertions.assertNotNull(actualUser);
        Assertions.assertNotNull(actualUser.getId());
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertEquals(expectedLogin, actualUser.getLogin());
        Assertions.assertEquals(expectedIsActive, actualUser.isActive());
    }

    @Test
    public void givenAnInvalidNullLogin_whenCallNewUserAndValidate_thenShouldReceiveError() {
        final String expectedLogin = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'login' should be informed";
        final var expectedName = "João Silva";
        final var expectedIsActive = true;

        final var actualUser = User.newUser(expectedName, expectedLogin, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).getMessage());
    }
}
