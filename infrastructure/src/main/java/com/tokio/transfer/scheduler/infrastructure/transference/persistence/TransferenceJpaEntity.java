package com.tokio.transfer.scheduler.infrastructure.transference.persistence;

import com.tokio.transfer.scheduler.domain.transference.Transference;
import com.tokio.transfer.scheduler.domain.transference.TransferenceID;
import com.tokio.transfer.scheduler.domain.user.UserID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Transference")
@Table(name = "transferences")
public class TransferenceJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "source_account", nullable = false)
    private String sourceAccount;

    @Column(name = "destination_account", length = 4000)
    private String destinationAccount;

    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2) NOT NULL DEFAULT 0.00")
    private BigDecimal amount;

    @Column(name = "transfer_date", nullable = false)
    private LocalDate transferDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public TransferenceJpaEntity() {}

    private TransferenceJpaEntity(
            final String id,
            final String userId,
            final String sourceAccount,
            final String destinationAccount,
            final BigDecimal amount,
            final LocalDate transferDate,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        this.id = id;
        this.userId = userId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transferDate = transferDate;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static TransferenceJpaEntity from(final Transference aTransference) {
        return new TransferenceJpaEntity(
                aTransference.getId().getValue(),
                aTransference.getUserID().getValue(),
                aTransference.getSourceAccount(),
                aTransference.getDestinationAccount(),
                aTransference.getAmount().getValue(),
                aTransference.getTransferDate().getValue(),
                aTransference.isActive(),
                aTransference.getCreatedAt(),
                aTransference.getUpdatedAt(),
                aTransference.getDeletedAt()
        );
    }

    public Transference toAggregate() {
        return Transference.with(
                TransferenceID.from(getId()),
                UserID.from(getUserId()),
                getSourceAccount(),
                getDestinationAccount(),
                getAmount().doubleValue(),
                getTransferDate(),
                isActive(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
}
