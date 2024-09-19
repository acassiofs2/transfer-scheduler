package com.tokio.transfer.scheduler.domain.exceptions;

import com.tokio.transfer.scheduler.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
