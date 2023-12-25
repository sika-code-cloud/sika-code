package com.sika.code.demo.sharding.pojo.query;

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
public class YearWeekQuery extends BaseQuery {
    private LocalDate yearWeekDate;
}
