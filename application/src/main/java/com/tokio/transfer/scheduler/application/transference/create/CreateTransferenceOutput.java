package com.tokio.transfer.scheduler.application.transference.create;

import com.tokio.transfer.scheduler.domain.transference.Transference;

public class CreateTransferenceOutput {
    private String id;

    private CreateTransferenceOutput(String id) {
        this.id = id;
    }

    public CreateTransferenceOutput from(final String anId) {
        return new CreateTransferenceOutput(anId);
    }

    public CreateTransferenceOutput from(final Transference anTransference) {
        return new CreateTransferenceOutput(anTransference.getId().getValue());
    }

    public String getId() {
        return id;
    }
}
