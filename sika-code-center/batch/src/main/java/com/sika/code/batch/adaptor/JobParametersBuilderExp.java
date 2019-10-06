package com.sika.code.batch.adaptor;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.dto.JobParametersDTO;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import lombok.Getter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 * 拓展JobParametersBuilder功能
 *
 * @author daiqi
 * @create 2019-09-29 23:12
 */
@Getter
public class JobParametersBuilderExp {
    private static final String DATA_DTO_KEY = "dataDTO";
    private static final String TIME_KEY = "time";
    private JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

    public static void removeDataDTO() {
        ThreadLocalManager.removeAll(DATA_DTO_KEY);
    }

    private static void addDataDTO(JobParametersDTO dataDTO) {
        // 放入threadLocal中
        ThreadLocalManager.setThreadLocalAndInheritable(DATA_DTO_KEY, dataDTO);
    }

    private static void addData(Object data) {
        addDataDTO(new JobParametersDTO(data));
    }

    public static JobParameters build(Object data) {
        return build(data, true, true);
    }

    public static JobParameters build(Object data, boolean addDataToJobParameters) {
        return build(data, addDataToJobParameters, true);
    }

    /**
     * <p>
     * 构建JobParameters
     * </p>
     *
     * @param data          ： 传输的数据
     * @param repeatExecute : 重复执行的标志
     * @return org.springframework.batch.core.JobParameters
     * @author sikadai
     * @date 2019/9/30 23:50
     */
    public static JobParameters build(Object data, boolean addDataToJobParameters, boolean repeatExecute) {
        addData(data);
        JobParametersBuilder builder = new JobParametersBuilder();
        if (addDataToJobParameters) {
            builder.addString(DATA_DTO_KEY, JSONUtil.toJSONString(data));
        }
        if (repeatExecute) {
            builder.addLong(TIME_KEY, System.nanoTime());
        }
        return builder.toJobParameters();
    }

    /**
     * @return com.sika.code.batch.dto.JobParametersDTO
     * @author sikadai
     * @date 2019/9/29 23:27
     */
    protected static JobParametersDTO from() {
        return (JobParametersDTO) ThreadLocalManager.getThreadLocalAndInheritable(DATA_DTO_KEY);
    }

    /**
     * <p>
     * 将jobParametersDataStr中的data值转化为泛型对应的对象
     * </p>
     *
     * @return T
     * @author sikadai
     * @date 2019/9/29 23:32
     */
    public static <T> T fromData() {
        JobParametersDTO jobParametersDTO = from();
        if (BaseUtil.isNull(jobParametersDTO)) {
            return null;
        }
        return (T) jobParametersDTO.getData();
    }
}
