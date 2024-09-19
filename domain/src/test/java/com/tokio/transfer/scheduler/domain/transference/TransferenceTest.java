package com.tokio.transfer.scheduler.domain.transference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransferenceTest {

    @Test
    public void givenAValidParams_whenCallNewTransference_thenInstantiateATransference() {
        final var expectedSourceAccount = "XXXXXXXXXX";
        final var expectedDestinationAccount = "XXXXXXXXXX";
        final var expectedAmount = 99.99;
        final var expectedTax = 2.5;
        final var expectedTransferDate = "25/09/2024";

        final var actualTransference =
                Transference.newTransfer(expectedSourceAccount, expectedDestinationAccount, expectedValue, expectedTax, transferDate);

        Assertions.assertNotNull(actualTransference);
        Assertions.assertNotNull(actualTransference.getId());
        Assertions.assertEquals(expectedSourceAccount, actualTransference.getSourceAccount());
        Assertions.assertEquals(expectedDestinationAccount, actualTransference.getDestinationAccount());
        Assertions.assertEquals(expectedAmount, actualTransference.getAmount());
        Assertions.assertEquals(expectedTax, actualTransference.getTax());
        Assertions.assertEquals(expectedTransferDate, actualTransference.getTransferDate());
        Assertions.assertNotNull(actualTransference.getCreatedAt);
        Assertions.assertNotNull(actualTransference.getUpdatedAt());
        Assertions.assertNotNull(actualTransference.getDeletedAt());
    }
}
