package com.sika.code.no.strategy.format;

import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.common.date.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 编号生成策略
 * </p>
 *
 * @author daiqi
 * @date 2019/5/31 10:23
 * @return
 */
@Getter
@AllArgsConstructor
public enum NoGeneratorTimeFormatStrategy implements NoGeneratorFormatStrategy, TypeEnumInf {
    /**
     * 编号生成策略 --- 时间策略
     */
    DAY(1, "yyyyMMdd", "精确到天"),
    HOUR(2, "yyyyMMddHH", "精确到时"),
    MINUTE(3, "yyyyMMddHHmm", "精确到分"),
    SECOND(4, "yyyyMMddHHmmss", "精确到秒"),
    MILLISECOND(5, "yyyyMMddHHmmssSSS", "精确到毫秒"),
    SECOND_SHORT_YEAR(6, "yyMMddHHmmss", "短年份-精确到秒"),
    MILLISECOND_SHORT_YEAR(7, "yyMMddHHmmssSSS", "短年份-精确到毫秒"),
    ;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 格式化
     */
    private String format;
    /**
     * 描述
     */
    private String desc;


    @Override
    public String generateStr(int count) {
        return DateUtil.format(DateUtil.date(), this.getFormat());
    }
}
