package com.tokio.transfer.scheduler.infrastructure.transference;

import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.user.UserID;
import com.tokio.transfer.scheduler.domain.utils.DateUtils;
import com.tokio.transfer.scheduler.infrastructure.MySQLGatewayTest;
import com.tokio.transfer.scheduler.infrastructure.transference.persistence.TransferenceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class TransferenceMySQLGatewayTest {

    @Autowired
    private TransferenceMySQLGateway transferenceGateway;

    @Autowired
    private TransferenceRepository transferenceRepository;

    @Test
    public void givenAValidTransference_whenCallsCreate_shouldReturnANewTransference() {
        final var userId = UserID.unique();
        final var expectedSourceAccount = "0123456789";
        final var expectedDestinationAccount = "9876543210";
        final var expectedAmount = 99.99;
        final var expectedTransferDate = DateUtils.now().plusDays(11);
        final var expectedIsActive = true;

        final var aTransference =
                Transference.newTransference(userId, expectedSourceAccount, expectedDestinationAccount, expectedAmount, expectedTransferDate, expectedIsActive);

        Assertions.assertEquals(0, transferenceRepository.count());

        final var actualTransference = transferenceGateway.create(aTransference);

        Assertions.assertEquals(1, transferenceRepository.count());

        Assertions.assertEquals(aTransference.getId(), actualTransference.getId());
        Assertions.assertEquals(aTransference.getUserID(), actualTransference.getUserID());
        Assertions.assertEquals(aTransference.getSourceAccount(), actualTransference.getSourceAccount());
        Assertions.assertEquals(aTransference.getDestinationAccount(), actualTransference.getDestinationAccount());
        Assertions.assertEquals(aTransference.getAmount(), actualTransference.getAmount());
        Assertions.assertEquals(aTransference.getTransferDate(), actualTransference.getTransferDate());
        Assertions.assertEquals(aTransference.isActive(), actualTransference.isActive());
    }

    @Test
    public void givenPrePersistedTransferences_whenCallsFindAll_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 2;

        final var transference1 = Transference.newTransference
                ("0123456789", "9876543210", 99.99, DateUtils.now().plusDays(11), true);
        final var transference2 = Transference.newTransference
                ("1111111111", "2222222222", 79.99, DateUtils.now().plusDays(21), true);


    }
}
