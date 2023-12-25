package com.sika.code.demo.sharding.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 * 年库周表 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:18
 */
@Getter
@Setter
@TableName("sika_year_week")
public class YearWeekPO extends BasePO {
    private LocalDate yearWeekDate;
}
