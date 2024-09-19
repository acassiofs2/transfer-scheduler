package com.tokio.transfer.scheduler.domain;

public abstract class ValueObject<T> {

    public T value;

    public abstract T getValue();
}
