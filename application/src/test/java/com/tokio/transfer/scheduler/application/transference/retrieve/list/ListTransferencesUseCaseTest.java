package com.tokio.transfer.scheduler.application.transference.retrieve.list;

import com.tokio.transfer.scheduler.application.UseCaseTest;
import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;
import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;
import com.tokio.transfer.scheduler.domain.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ListTransferencesUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListTransferencesUseCase useCase;

    @Mock
    private TransferenceGateway transferenceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(transferenceGateway);
    }

    @Test
    public void givenAValidQuery_whenCallsListTransferences_thenShouldReturnTransferences() {
        final var expectedTransferDate = DateUtils.now().plusDays(11);
        final var transferences = List.of(
                Transference.newTransference
                        ("0123456789", "9876543210", 99.99, expectedTransferDate, true),
                Transference.newTransference
                        ("1111111111", "2222222222", 79.99, expectedTransferDate, true)
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
                new Pagination<>(expectedPage, expectedPerPage, transferences.size(), transferences);

        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(TransferenceListOutput::from);

        when(transferenceGateway.findAll(eq(aQuery)))
                .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        final var items = expectedResult.getItems();

        Assertions.assertEquals(expectedItemsCount, actualResult.getItems().size());
        Assertions.assertEquals(items.get(0).getId(), actualResult.getItems().get(0).getId());
        Assertions.assertEquals(items.get(1).getId(), actualResult.getItems().get(1).getId());
        Assertions.assertEquals(expectedPage, actualResult.getCurrentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.getPerPage());
        Assertions.assertEquals(transferences.size(), actualResult.getTotal());
    }

    @Test
    public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyTransferences() {
        final var transferences = List.<Transference>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
                new Pagination<>(expectedPage, expectedPerPage, 0, transferences);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(TransferenceListOutput::from);

        when(transferenceGateway.findAll(eq(aQuery)))
                .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.getItems().size());
        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedPage, actualResult.getCurrentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.getPerPage());
        Assertions.assertEquals(0, actualResult.getTotal());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedErrorMessage = "Gateway error";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(transferenceGateway.findAll(eq(aQuery)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException =
                Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
