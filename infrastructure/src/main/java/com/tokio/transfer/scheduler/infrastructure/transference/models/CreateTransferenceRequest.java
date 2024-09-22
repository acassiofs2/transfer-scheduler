package com.tokio.transfer.scheduler.infrastructure.transference.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransferenceRequest {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("source_account")
    private String sourceAccount;
    @JsonProperty("destination_account")
    private String destinationAccount;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("transfer_date")
    private LocalDate transferDate;
    @JsonProperty("is_active")
    private Boolean active;

    public String getUserId() {
        return userId;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public Boolean getActive() {
        return active;
    }
}
