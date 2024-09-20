package com.tokio.transfer.scheduler.domain.user;

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
        Assertions.assertEquals(expectedName, actualUser.getLogin());
        Assertions.assertEquals(expectedName, actualUser.isActive());
    }
}
