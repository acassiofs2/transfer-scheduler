package com.tokio.transfer.scheduler.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}