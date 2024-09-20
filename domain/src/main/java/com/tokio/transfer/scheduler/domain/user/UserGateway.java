package com.tokio.transfer.scheduler.domain.user;

import com.tokio.transfer.scheduler.domain.pagination.Pagination;
import com.tokio.transfer.scheduler.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    User create(User aUser);

    void deleteById(UserID anId);

    Optional<User> findById(UserID anId);

    User update(User aUser);

    Pagination<User> findALl(SearchQuery aQuery);

    List<UserID> existsById(Iterable<UserID> ids);
}
