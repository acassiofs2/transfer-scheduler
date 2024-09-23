package com.tokio.transfer.scheduler.infrastructure.transference.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokio.transfer.scheduler.domain.Date;
import com.tokio.transfer.scheduler.domain.Decimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class TransferenceListResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("source_account")
    private String sourceAccount;
    @JsonProperty("destination_account")
    private String destinationAccount;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("transfer_date")
    private String transferDate;
    @JsonProperty("is_active")
    private Boolean active;
    @JsonProperty("created_at")
    private String createdAt;

    public TransferenceListResponse(
            final String id,
            final String userId,
            final String sourceAccount,
            final String destinationAccount,
            final BigDecimal amount,
            final LocalDate transferDate,
            final boolean active,
            final Instant createdAt
    ) {
        this.id = id;
        this.userId = userId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = Decimal.of(amount, 2).toString();
        this.transferDate = Date.of(transferDate).toString();
        this.active = active;
        this.createdAt = Date.of(createdAt).toString();
    }
}
