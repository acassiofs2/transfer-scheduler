package com.tokio.transfer.scheduler.infrastructure.configuration.usecases;

import com.tokio.transfer.scheduler.application.transference.create.CreateTransferenceUseCase;
import com.tokio.transfer.scheduler.application.transference.create.DefaultCreateTransferenceUseCase;
import com.tokio.transfer.scheduler.application.transference.retrieve.list.DefaultListTransferencesUseCase;
import com.tokio.transfer.scheduler.application.transference.retrieve.list.ListTransferencesUseCase;
import com.tokio.transfer.scheduler.domain.transference.TransferenceGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferenceUseCaseConfig {

    private final TransferenceGateway transferenceGateway;

    public TransferenceUseCaseConfig(final TransferenceGateway transferenceGateway) {
        this.transferenceGateway = transferenceGateway;
    }

    @Bean
    public CreateTransferenceUseCase createTransferenceUseCase() {
        return new DefaultCreateTransferenceUseCase(transferenceGateway);
    }

    @Bean
    public ListTransferencesUseCase listTransferencesUseCase() {
        return new DefaultListTransferencesUseCase(transferenceGateway);
    }


}
