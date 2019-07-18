package com.sika.code.informer.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.log.util.LogUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.informer.BaseInformer;
import com.google.common.collect.Lists;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-06-30 0:40
 */
public class InformerUtil {

    public static void inform(Exception exception, BaseInformer informer, Logger logger) {
        inform(exception, Lists.newArrayList(informer), logger);
    }

    /**
     * 通知
     */
    public static void inform(Exception exception, List<BaseInformer> informersDefault, Logger logger) {
        try {
            if (BaseUtil.isNull(exception)) {
                LogUtil.error("异常通知", "businessExceptionDTO为空", logger);
                return;
            }
            logger.error(exception.getMessage(), exception);
            List<BaseInformer> informers = null;
            if (exception instanceof BusinessException) {
                informers = ((BusinessException) exception).getInformers();
            }
            if (CollUtil.isEmpty(informers)) {
                informers = informersDefault;
            }
            if (CollUtil.isEmpty(informers)) {
                return;
            }
            String exceptionDetail = ExceptionUtil.stacktraceToString(exception);
            informers.stream()
                    .filter(informer -> BaseUtil.isNotNull(informer))
                    .forEach(informer -> informer.inform(exceptionDetail));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
