package io.divolte.server.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.MoreObjects;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Properties;

@ParametersAreNonnullByDefault
public final class HdfsConfiguration extends SinkTypeConfiguration {

    private final Optional<Properties> client;

    @JsonCreator
    HdfsConfiguration(final boolean enabled, final int bufferSize, final int threads, final Optional<Properties> client) {
        super(bufferSize, threads, enabled);
        // Defensive copy: ensure our copy remains immutable.
        this.client = client.map(properties -> (Properties) properties.clone());
    }

    @Override
    protected MoreObjects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("client", client);
    }

    public Optional<Properties> getClient() {
        // Defensive copy: we can't stop callers from modifying what we return.
        return client.map(properties -> (Properties) properties.clone());
    }
}
