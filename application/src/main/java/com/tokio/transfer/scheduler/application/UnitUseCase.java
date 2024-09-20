package com.tokio.transfer.scheduler.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN anIn);
}
