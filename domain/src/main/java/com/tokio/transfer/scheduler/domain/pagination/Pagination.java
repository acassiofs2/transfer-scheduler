package com.tokio.transfer.scheduler.domain.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pagination<T> {

    private final int currentPage;
    private final int perPage;
    private final long total;
    private final List<T> items;

    public Pagination(int currentPage, int perPage, long total, List<T> items) {
        this.currentPage = currentPage;
        this.perPage = perPage;
        this.total = total;
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = getItems().stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new Pagination<>(getCurrentPage(), getPerPage(), getTotal(), aNewList);
    }
}
