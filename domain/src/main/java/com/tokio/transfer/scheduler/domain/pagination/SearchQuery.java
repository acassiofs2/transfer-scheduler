package com.tokio.transfer.scheduler.domain.pagination;

public class SearchQuery {
    private final int page;
    private final int perPage;
    private final String terms;
    private final String sort;
    private final String direction;

    public SearchQuery(int page, int perPage, String terms, String sort, String direction) {
        this.page = page;
        this.perPage = perPage;
        this.terms = terms;
        this.sort = sort;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getTerms() {
        return terms;
    }

    public String getSort() {
        return sort;
    }

    public String getDirection() {
        return direction;
    }
}
