package com.tokio.transfer.scheduler.domain.user;

import com.tokio.transfer.scheduler.domain.AggregateRoot;
import com.tokio.transfer.scheduler.domain.validation.ValidationHandler;

public class User extends AggregateRoot<UserID> {

    private String name;
    private String login;
    private boolean active;

    private User(
            final UserID anId,
            final String aName,
            final String aLogin,
            final boolean isActive
    ) {
        super(anId);
        this.name = aName;
        this.login = aLogin;
        this.active = isActive;
    }

    public static User newUser(final String aName, final String aLogin, final boolean isActive) {
        final var id = UserID.unique();
        return new User(id, aName, aLogin, isActive);
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }
}
