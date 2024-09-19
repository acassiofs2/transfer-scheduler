package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
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
        final var expectedIsActive = true;

        final var actualTransference =
                Transference.newTransference(expectedSourceAccount, expectedDestinationAccount, expectedAmount, expectedTax, expectedTransferDate, expectedIsActive);

        Assertions.assertNotNull(actualTransference);
        Assertions.assertNotNull(actualTransference.getId());
        Assertions.assertEquals(expectedSourceAccount, actualTransference.getSourceAccount());
        Assertions.assertEquals(expectedDestinationAccount, actualTransference.getDestinationAccount());
        Assertions.assertEquals(Decimal.of(expectedAmount, 2), actualTransference.getAmount());
        Assertions.assertEquals(Decimal.of(expectedTax, 1), actualTransference.getTax());
        Assertions.assertEquals(Date.of(expectedTransferDate), actualTransference.getTransferDate());
        Assertions.assertNotNull(actualTransference.getCreatedAt());
        Assertions.assertNotNull(actualTransference.getUpdatedAt());
        Assertions.assertNull(actualTransference.getDeletedAt());
    }
}
