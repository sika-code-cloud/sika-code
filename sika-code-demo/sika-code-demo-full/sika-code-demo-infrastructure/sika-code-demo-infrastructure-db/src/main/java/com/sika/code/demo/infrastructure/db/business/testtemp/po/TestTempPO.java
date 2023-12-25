package com.sika.code.demo.infrastructure.db.business.testtemp.po;

import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

/**
 * <p>
 * 测试tem表 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:39
 */
@Getter
@Setter
@TableName("lf_test_temp")
public class TestTempPO extends BaseBizPO {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人标识
     */
    private String createBy;
    /**
     * 最后更新人标识
     */
    private String updateBy;
    /**
     * 编号
     */
    private String testNo;
    /**
     * 流程名称
     */
    private String testName;
    /**
     * 记录日期
     */
    private LocalDate recordDate;
}
