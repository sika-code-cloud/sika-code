package com.sika.code.demo.interfaces.common.configure;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.pause.PauseDetector;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import io.micrometer.core.instrument.push.PushMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exemplars.ExemplarSampler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

/**
 * CustomerLoggingMeterRegistry
 *
 * @author : daiqi
 * @date : 2023-09-05
 */
@Slf4j
public class CustomerPromethuesMeterRegistry extends PrometheusMeterRegistry {
    public CustomerPromethuesMeterRegistry(PrometheusConfig config) {
        super(config);
    }

    public CustomerPromethuesMeterRegistry(PrometheusConfig config, CollectorRegistry registry, Clock clock) {
        super(config, registry, clock);
    }

    public CustomerPromethuesMeterRegistry(PrometheusConfig config, CollectorRegistry registry, Clock clock,
        ExemplarSampler exemplarSampler) {
        super(config, registry, clock, exemplarSampler);
    }

    @Override
    public  <T> Gauge newGauge(Meter.Id id, T obj, ToDoubleFunction<T> valueFunction) {
        return super.newGauge(id, obj, valueFunction);
    }

    @Override
    protected Timer newTimer(Meter.Id id, DistributionStatisticConfig distributionStatisticConfig,
        PauseDetector pauseDetector) {
        return super.newTimer(id, distributionStatisticConfig, pauseDetector);
    }

    @Override
    protected Meter newMeter(Meter.Id id, Meter.Type type, Iterable<Measurement> measurements) {
        return super.newMeter(id, type, measurements);
    }

    @Override
    protected <T> FunctionTimer newFunctionTimer(Meter.Id id, T obj, ToLongFunction<T> countFunction,
        ToDoubleFunction<T> totalTimeFunction, TimeUnit totalTimeFunctionUnit) {
        return super.newFunctionTimer(id, obj, countFunction, totalTimeFunction, totalTimeFunctionUnit);
    }

    @Override
    protected <T> FunctionCounter newFunctionCounter(Meter.Id id, T obj, ToDoubleFunction<T> countFunction) {
        return super.newFunctionCounter(id, obj, countFunction);
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    @Override
    protected DistributionStatisticConfig defaultHistogramConfig() {
        return super.defaultHistogramConfig();
    }
}
