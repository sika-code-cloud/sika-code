package com.sika.code.demo.interfaces.common.configure;

import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Statistic;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.pause.PauseDetector;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import io.micrometer.core.instrument.push.PushMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * CustomerLoggingMeterRegistry
 *
 * @author : daiqi
 * @date : 2023-09-05
 */
@Slf4j
public class CustomerLoggingMeterRegistry extends PushMeterRegistry {
    private SimpleMeterRegistry simpleMeterRegistry;
    private CustomerPromethuesMeterRegistry customerPromethuesMeterRegistry;

    public CustomerLoggingMeterRegistry() {
        this(LoggingRegistryConfig.DEFAULT, Clock.SYSTEM);
    }

    public CustomerLoggingMeterRegistry(StepRegistryConfig config, Clock clock) {
        super(config, clock);
        start(new NamedThreadFactory("logging-metrics-publisher"));
    }

    @Override
    protected void publish() {
        log.info(getMetersAsString());
    }

    @Incubating(since = "1.9.0")
    public String getMetersAsString() {
        return this.getMeters().stream().sorted(Comparator.comparing(meter -> meter.getId().getName()))
            .map(this::toString).collect(Collectors.joining("\n"));
    }

    private String toString(Meter meter) {
        Meter.Id id = meter.getId();
        String tags = id.getTags().stream().map(this::toString).collect(Collectors.joining(", "));
        String baseUnit = id.getBaseUnit();
        String meterUnitSuffix = baseUnit != null ? " " + baseUnit : "";
        String measurements = StreamSupport.stream(meter.measure().spliterator(), false)
            .map((measurement) -> toString(measurement, meterUnitSuffix)).collect(Collectors.joining(", "));
        return String.format("%s(%s)[%s]; %s", id.getName(), id.getType(), tags, measurements);
    }

    private String toString(Measurement measurement, String meterUnitSuffix) {
        Statistic statistic = measurement.getStatistic();
        return String.format("%s=%s%s", statistic.toString().toLowerCase(), measurement.getValue(), "");
    }

    private String toString(Tag tag) {
        return String.format("%s='%s'", tag.getKey(), tag.getValue());
    }

    @Override
    protected <T> Gauge newGauge(Meter.Id id, T obj, ToDoubleFunction<T> valueFunction) {
        return customerPromethuesMeterRegistry.newGauge(id, obj, valueFunction);
    }

    @Override
    protected Counter newCounter(Meter.Id id) {
        return customerPromethuesMeterRegistry.newCounter(id);
    }

    @Override
    protected Timer newTimer(Meter.Id id, DistributionStatisticConfig distributionStatisticConfig,
        PauseDetector pauseDetector) {
        return customerPromethuesMeterRegistry.newTimer(id, distributionStatisticConfig, pauseDetector);
    }

    @Override
    protected DistributionSummary newDistributionSummary(Meter.Id id,
        DistributionStatisticConfig distributionStatisticConfig, double scale) {
        return customerPromethuesMeterRegistry.newDistributionSummary(id, distributionStatisticConfig, scale);
    }

    @Override
    protected Meter newMeter(Meter.Id id, Meter.Type type, Iterable<Measurement> measurements) {
        return customerPromethuesMeterRegistry.newMeter(id, type, measurements);
    }

    @Override
    protected <T> FunctionTimer newFunctionTimer(Meter.Id id, T obj, ToLongFunction<T> countFunction,
        ToDoubleFunction<T> totalTimeFunction, TimeUnit totalTimeFunctionUnit) {
        return customerPromethuesMeterRegistry.newFunctionTimer(id, obj, countFunction, totalTimeFunction,
            totalTimeFunctionUnit);
    }

    @Override
    protected <T> FunctionCounter newFunctionCounter(Meter.Id id, T obj, ToDoubleFunction<T> countFunction) {
        return customerPromethuesMeterRegistry.newFunctionCounter(id, obj, countFunction);
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    @Override
    protected DistributionStatisticConfig defaultHistogramConfig() {
        return customerPromethuesMeterRegistry.defaultHistogramConfig();
    }

    public void setSimpleMeterRegistry(SimpleMeterRegistry simpleMeterRegistry) {
        this.simpleMeterRegistry = simpleMeterRegistry;
    }

    public void setCustomerPromethuesMeterRegistry(CustomerPromethuesMeterRegistry customerPromethuesMeterRegistry) {
        this.customerPromethuesMeterRegistry = customerPromethuesMeterRegistry;
    }
}
