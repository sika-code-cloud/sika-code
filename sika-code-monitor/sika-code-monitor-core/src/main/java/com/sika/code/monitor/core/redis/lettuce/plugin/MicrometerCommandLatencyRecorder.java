/*
 * Copyright 2011-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sika.code.monitor.core.redis.lettuce.plugin;

import com.sika.code.monitor.core.invoke.config.InvokeTimedConfig;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnums;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.lettuce.core.internal.LettuceAssert;
import io.lettuce.core.metrics.CommandLatencyId;
import io.lettuce.core.metrics.CommandLatencyRecorder;
import io.lettuce.core.metrics.MicrometerOptions;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.netty.channel.local.LocalAddress;

import java.net.SocketAddress;

/**
 * Micrometer implementation of {@link CommandLatencyRecorder}
 *
 * @author Steven Sheehy
 * @since 6.1
 */
public class MicrometerCommandLatencyRecorder implements CommandLatencyRecorder {

    static final String LABEL_COMMAND = "command";

    static final String LABEL_LOCAL = "local";

    static final String LABEL_REMOTE = "remote";

    private final MeterRegistry meterRegistry;

    private final MicrometerOptions options;
    private final InvokeTimedMetrics invokeTimedMetrics;

    /**
     * Create a new {@link MicrometerCommandLatencyRecorder} instance given {@link MeterRegistry} and
     * {@link MicrometerOptions}.
     *
     * @param meterRegistry
     * @param options
     */
    public MicrometerCommandLatencyRecorder(MeterRegistry meterRegistry, MicrometerOptions options,
        InvokeTimedMetrics invokeTimedMetrics) {

        LettuceAssert.notNull(meterRegistry, "MeterRegistry must not be null");
        LettuceAssert.notNull(options, "MicrometerOptions must not be null");

        this.meterRegistry = meterRegistry;
        this.options = options;
        this.invokeTimedMetrics = invokeTimedMetrics;
    }

    @Override
    public void recordCommandLatency(SocketAddress local, SocketAddress remote, ProtocolKeyword protocolKeyword,
        long firstResponseLatency, long completionLatency) {

        if (!isEnabled()) {
            return;
        }

        CommandLatencyId commandLatencyId = createId(local, remote, protocolKeyword);

        firstResponseTimer(commandLatencyId, firstResponseLatency);
        completionTimer(commandLatencyId, completionLatency);
    }

    @Override
    public boolean isEnabled() {
        return options.isEnabled();
    }

    private CommandLatencyId createId(SocketAddress local, SocketAddress remote, ProtocolKeyword commandType) {
        return CommandLatencyId.create(options.localDistinction() ? local : LocalAddress.ANY, remote, commandType);
    }

    protected void completionTimer(CommandLatencyId commandLatencyId, Long invokeTimeNs) {
        InvokeTimedConfig invokeTimeNsdConfig =
            invokeTimedMetrics.getInstance(meterRegistry, InvokeTimedTypeEnums.REDIS_LETTUCE_COMPLETION);
        Tags tags = Tags.of(LABEL_COMMAND, commandLatencyId.commandType().name())
            .and(LABEL_LOCAL, commandLatencyId.localAddress().toString())
            .and(LABEL_REMOTE, commandLatencyId.remoteAddress().toString());
        invokeTimedMetrics.collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    protected void firstResponseTimer(CommandLatencyId commandLatencyId, Long invokeTimeNs) {

        InvokeTimedConfig invokeTimeNsdConfig =
            invokeTimedMetrics.getInstance(meterRegistry, InvokeTimedTypeEnums.REDIS_LETTUCE_FIRST_RESPONSE);
        Tags tags = Tags.of(LABEL_COMMAND, commandLatencyId.commandType().name())
            .and(LABEL_LOCAL, commandLatencyId.localAddress().toString())
            .and(LABEL_REMOTE, commandLatencyId.remoteAddress().toString());
        invokeTimedMetrics.collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

}
