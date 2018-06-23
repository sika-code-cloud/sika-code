package com.easy.cloud.core.worker.common.constant;

/**
 * 任务常量类
 *
 * @author daiqi
 * @create 2018-06-22 10:06
 */
public class EcWorkerConstant {
    /**
     * <p>
     * 任务工作类型常量类
     * </p>
     *
     * @author daiqi
     * @date 2018/6/22 10:08
     */
    public static class EcJobType {
        /** 工作类型---rest---1 */
        public static final int REST = 1;
        /** 工作类型---mq---1 */
        public static final int MQ = 2;
        /** 工作类型---redis---1 */
        public static final int REDIS = 3;
    }
}
