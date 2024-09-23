package com.tokio.transfer.scheduler.infrastructure.transference.presenters;

import com.tokio.transfer.scheduler.application.transference.retrieve.list.TransferenceListOutput;
import com.tokio.transfer.scheduler.infrastructure.transference.models.TransferenceListResponse;

public interface TransferenceApiPresenter {

    static TransferenceListResponse present(final TransferenceListOutput output) {
        return new TransferenceListResponse(
                output.getId().getValue(),
                output.getUserId().getValue(),
                output.getSourceAccount(),
                output.getDestinationAccount(),
                output.getAmount().getValue(),
                output.getTax(),
                output.getTransferDate().getValue(),
                output.isActive(),
                output.getCreatedAt()
        );
    }
}
