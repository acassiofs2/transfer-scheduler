package com.tokio.transfer.scheduler.infrastructure.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {

    private SpecificationUtils() {
    }

    public static <T> Specification<T> eq(final String prop, final String term) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get(prop)), term);
    }
}
