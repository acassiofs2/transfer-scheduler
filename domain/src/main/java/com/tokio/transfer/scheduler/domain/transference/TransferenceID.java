package com.tokio.transfer.scheduler.domain.transference;

import com.tokio.transfer.scheduler.domain.Identifier;
import com.tokio.transfer.scheduler.domain.utils.IdUtils;

import java.util.Objects;

public class TransferenceID extends Identifier<String> {

    private TransferenceID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static TransferenceID unique() {
        return TransferenceID.from(IdUtils.uuid());
    }

    public static TransferenceID from(final String anId) {
        return new TransferenceID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TransferenceID that = (TransferenceID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
