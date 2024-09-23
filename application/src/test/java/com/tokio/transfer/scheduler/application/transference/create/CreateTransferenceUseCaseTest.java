package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.application.UseCaseTest;
import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;
import com.tokio.transfer.scheduler.domain.user.UserID;
import com.tokio.transfer.scheduler.domain.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateTransferenceUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateTransferenceUseCase useCase;

    @Mock
    private TransferenceGateway transferenceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(transferenceGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateTransference_shouldReturnTransferenceId() {
        final var userId = UserID.unique();
        final var expectedSourceAccount = "0123456789";
        final var expectedDestinationAccount = "9876543210";
        final var expectedAmount = 99.99;
        final var expectedTransferDate = DateUtils.now().plusDays(11);
        final var expectedIsActive = true;

        final var aCommand =
                CreateTransferenceCommand.with(userId.getValue(), expectedSourceAccount, expectedDestinationAccount, expectedAmount, expectedTransferDate, expectedIsActive);

        when(transferenceGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.getId());

        verify(transferenceGateway, times(1)).create(argThat(aTransference ->
                Objects.equals(expectedSourceAccount, aTransference.getSourceAccount())
                    && Objects.equals(expectedDestinationAccount, aTransference.getDestinationAccount())
                    && Objects.equals(Decimal.of(expectedAmount, 2), aTransference.getAmount())
                    && Objects.equals(Date.of(expectedTransferDate), aTransference.getTransferDate())
                    && Objects.equals(expectedIsActive, aTransference.isActive())
        ));
    }

    @Test
    public void givenATaxNotApplicable_whenCallsCreateTransference_thenShouldReturnDomainException() {
        final var userId = UserID.unique();
        final var expectedTransferDate = DateUtils.now().plusDays(81);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Nenhuma taxa aplicável para a data escolhida";
        final var expectedSourceAccount = "0123456789";
        final var expectedDestinationAccount = "9876543210";
        final var expectedAmount = 99.99;
        final var expectedIsActive = true;

        final var aCommand =
                CreateTransferenceCommand.with(userId.getValue(), expectedSourceAccount, expectedDestinationAccount, expectedAmount, expectedTransferDate, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().getMessage());

        Mockito.verify(transferenceGateway, times(0)).create(any());
    }
}
