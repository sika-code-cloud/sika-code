package com.sika.code.common.workspace.properties;

import com.sika.code.basic.constant.PropertiesConstant;
import com.sika.code.basic.util.BaseUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 工作空间
 *
 * @author daiqi
 * @create 2019-07-02 22:41
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = PropertiesConstant.WORKSPACE)
public class WorkspaceProperties {
    /**
     * 默认的工作空间编号
     */
    private static final Integer WORKER_ID_DEFAULT = 1;
    /**
     * 默认的数据中心编号
     */
    private static final Integer DATA_CENTER_ID_DEFAULT = 1;
    /**
     * 工作空间编号
     */
    private Integer workerId;
    /**
     * 数据中心编号
     */
    private Integer dataCenterId;

    /**  
     * <p>
     * 构建默认值
     * </p>
     *   
     * @author daiqi
     * @date 2019/7/2 22:53
     */  
    public WorkspaceProperties buildDefault() {
        if (BaseUtil.isNull(workerId)) {
            this.workerId = WORKER_ID_DEFAULT;
        }
        if (BaseUtil.isNull(dataCenterId)) {
            this.dataCenterId = DATA_CENTER_ID_DEFAULT;
        }
        return this;
    }
}
