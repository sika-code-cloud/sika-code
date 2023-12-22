package com.sika.code.demo.infrastructure.business.testtemp.pojo.dto;

import com.sika.code.demo.infrastructure.common.pojo.dto.BaseBizDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * <p>
 * 测试tem表 数据传输类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:37
 */
@Getter
@Setter
public class TestTempDTO extends BaseBizDTO {
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
