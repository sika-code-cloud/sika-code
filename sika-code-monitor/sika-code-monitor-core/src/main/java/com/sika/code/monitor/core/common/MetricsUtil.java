package com.sika.code.monitor.core.common;

import cn.hutool.core.util.StrUtil;
import io.micrometer.core.instrument.*;
import org.slf4j.Logger;

/**
 * Metrics
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
public class MetricsUtil {
    private static final String DOT = ".";

    public static Timer.Sample startTimer(MeterRegistry meterRegistry) {
        return Timer.start(meterRegistry);
    }

    public static Timer.Sample startTimer(Clock clock) {
        return Timer.start(clock);
    }

    public static void stopTimer(MeterRegistry meterRegistry, Timer.Sample sample, String metricsName, Tags metricsTags,
        String metricsDesc, Logger logger) {
        try {
            sample.stop(
                Timer.builder(metricsName).tags(metricsTags).description(metricsDesc).publishPercentileHistogram(false)
                    .publishPercentiles(0.50, 0.90, 0.95, 0.99).register(meterRegistry));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static LongTaskTimer.Sample startLongTimer(MeterRegistry meterRegistry, String metricsName, Tags metricsTags,
        String metricsDesc) {
        return LongTaskTimer.builder(metricsName).tags(metricsTags).description(metricsDesc).register(meterRegistry)
            .start();
    }

    public static void stopLongTime(LongTaskTimer.Sample sample) {
        sample.stop();
    }

    /**
     * 构建简化的ClassName
     *
     * @param className 类名
     * @return String
     */
    public static String buildSimplifyClassOrUriName(String className) {
        if (StrUtil.isBlank(className)) {
            return className;
        }
        if (!className.contains(DOT)) {
            return className;
        }
        return className.substring(className.lastIndexOf(DOT) + 1);
    }

    /**
     * 构建简化的MapperName
     *
     * @param mapperName Mapper的名称
     * @return String
     */
    public static String buildSimplifyMapperName(String mapperName) {
        if (StrUtil.isBlank(mapperName)) {
            return mapperName;
        }
        if (!mapperName.contains(DOT)) {
            return mapperName;
        }
        // mapperName格式为className+方法名称 - 因此截取倒数第二个.之后的字符串
        return mapperName.substring(mapperName.lastIndexOf(DOT, mapperName.lastIndexOf(DOT) - 1) + 1);
    }

}
