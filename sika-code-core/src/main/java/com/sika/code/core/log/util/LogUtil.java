package com.sika.code.core.log.util;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSONObject;
import com.yomahub.tlog.context.TLogContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Date;
import java.util.Locale;

/**
 * @author daiqi
 * @ClassName : LogUtil
 * @Description : 日志工具类
 * @date 2017年12月21日 上午11:49:24
 */
@Slf4j
public class LogUtil {
    public static final String SEPARATE = "**********************************************";
    /**
     * 开始换行符
     */
    public static final String START_LINE_BREAK = "\r";
    /**
     * 结束换行符
     */
    public static final String END_LINE_BREAK = "\r\n";
    public static final String TRACE_ID = "traceId";
    public static final String TRACE_COUNT = "traceCount";

    private static final int RANDOM_COUNT = 13;
    private static final String TRACE_ID_PREFIX = "SC";

    public static void putTraceId() {
        MDC.put(TRACE_ID, createTraceId());
    }

    public static String createTraceId() {

        // hutool的时间格式化比apache性能低75%左右 - 此处使用apache的DateFormatUtils.format时间格式化-
        String dateBody = DateFormatUtils.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN);
        // hutool的随机函数性能比该函数低80%左右 - 因此使用apache的RandomStringUtils.randomAlphanumeric随机函数生成
        String suffix = RandomStringUtils.randomAlphanumeric(RANDOM_COUNT).toUpperCase(Locale.ROOT);

        return TRACE_ID_PREFIX + dateBody + suffix;
    }

    public static String getTraceId() {
        return TLogContext.getTraceId();
//        return MDC.get(TRACE_ID);
    }

    public static void clearMDC() {
        MDC.clear();
    }

    /**
     * <p>
     * debug级别的统一格式日志
     * </p>
     *
     * @param logTitle  : String : 打印的日志主题
     * @param logDetail : Object : 打印的日志详情信息
     * @param logger    : Logger : 目标类的日志记录对象
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void debug(String logTitle, Object logDetail, Logger logger) {
        debug(logTitle, logDetail, logger, true);
    }

    /**
     * <p>
     * debug级别的统一格式日志
     * </p>
     *
     * @param logTitle   : String : 打印的日志主题
     * @param logDetail  : Object : 打印的日志详情信息
     * @param logger     : Logger : 目标类的日志记录对象
     * @param isNeedWrap : boolean : 是否需要换行 true需要 false不需要
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void debug(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        if (isNeedWrap) {
            logger.debug(START_LINE_BREAK);
        }
        logger.debug(startFormat(), arguments(logTitle));
        logger.debug(buildLogDetail(logDetail));
        logger.debug(endFormat(), arguments(logTitle));
        if (isNeedWrap) {
            logger.debug(END_LINE_BREAK);
        }
    }

    /**
     * <p>
     * info级别的统一格式日志
     * </p>
     *
     * @param logTitle  : String : 打印的日志主题
     * @param logDetail : Object : 打印的日志详情信息
     * @param logger    : Logger : 目标类的日志记录对象
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void info(String logTitle, Object logDetail, Logger logger) {
        info(logTitle, logDetail, logger, true);
    }

    /**
     * <p>
     * info级别的统一格式日志
     * </p>
     *
     * @param logTitle   : String : 打印的日志主题
     * @param logDetail  : Object : 打印的日志详情信息
     * @param logger     : Logger : 目标类的日志记录对象
     * @param isNeedWrap : boolean : 是否需要换行 true需要 false不需要
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void info(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        if (isNeedWrap) {
            logger.info(START_LINE_BREAK);
        }
        logger.info(startFormat(), arguments(logTitle));
        logger.info(buildLogDetail(logDetail));
        logger.info(endFormat(), arguments(logTitle));
        if (isNeedWrap) {
            logger.info(END_LINE_BREAK);
        }
    }

    /**
     * <p>
     * warn级别的统一格式日志
     * </p>
     *
     * @param logTitle  : String : 打印的日志主题
     * @param logDetail : Object : 打印的日志详情信息
     * @param logger    : Logger : 目标类的日志记录对象
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void warn(String logTitle, Object logDetail, Logger logger) {
        warn(logTitle, logDetail, logger, true);
    }

    /**
     * <p>
     * info级别的统一格式日志
     * </p>
     *
     * @param logTitle   : String : 打印的日志主题
     * @param logDetail  : Object : 打印的日志详情信息
     * @param logger     : Logger : 目标类的日志记录对象
     * @param isNeedWrap : boolean : 是否需要换行 true需要 false不需要
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void warn(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        if (isNeedWrap) {
            logger.warn(START_LINE_BREAK);
        }
        logger.warn(startFormat(), arguments(logTitle));
        logger.warn(buildLogDetail(logDetail));
        logger.warn(endFormat(), arguments(logTitle));
        if (isNeedWrap) {
            logger.warn(END_LINE_BREAK);
        }
    }

    /**
     * <p>
     * error级别的统一格式日志
     * </p>
     *
     * @param logTitle  : String : 打印的日志主题
     * @param logDetail : Object : 打印的日志详情信息
     * @param logger    : Logger : 目标类的日志记录对象
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void error(String logTitle, Object logDetail, Logger logger) {
        error(logTitle, logDetail, logger, true);
    }

    /**
     * <p>
     * error级别的统一格式日志
     * </p>
     *
     * @param logTitle   : String : 打印的日志主题
     * @param logDetail  : Object : 打印的日志详情信息
     * @param logger     : Logger : 目标类的日志记录对象
     * @param isNeedWrap : boolean : 是否需要换行 true需要 false不需要
     * @author daiqi 创建时间 2018年2月3日 下午5:21:06
     */
    public static void error(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        if (isNeedWrap) {
            logger.error(START_LINE_BREAK);
        }
        logger.error(startFormat(), arguments(logTitle));
        if (!(logDetail instanceof Throwable)) {
            logger.error(buildLogDetail(logDetail));
        } else {
            logger.error(((Throwable) logDetail).getMessage(), logDetail);
        }
        logger.error(endFormat(), arguments(logTitle));
        if (isNeedWrap) {
            logger.error(END_LINE_BREAK);
        }
    }

    public static String buildLogDetail(Object logDetail) {
        String detail;
        if (logDetail instanceof String) {
            detail = (String) logDetail;
        } else {
            detail = JSONObject.toJSONString(logDetail);
        }
        return detail;
    }

    public static String startFormat() {
        return "{}   start_logger:{}:start_logger   {}";
    }

    public static String endFormat() {
        return "{}   end_logger:{}:end_logger   {}";
    }

    public static Object[] arguments(String logTitle) {
        String[] arguments = {SEPARATE, logTitle, SEPARATE};
        return arguments;
    }
}
