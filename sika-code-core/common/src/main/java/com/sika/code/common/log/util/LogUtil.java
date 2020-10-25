package com.sika.code.common.log.util;

import com.sika.code.common.json.util.JSONUtil;
import org.slf4j.Logger;

/**
 * @author daiqi
 * @ClassName : LogUtil
 * @Description : 日志工具类
 * @date 2017年12月21日 上午11:49:24
 */
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
            detail = JSONUtil.toJSONString(logDetail);
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
