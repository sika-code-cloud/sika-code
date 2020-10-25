package com.zyd.blog.business.log.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 统计条目对象
 *
 * @author daiqi
 * @create 2020-09-18 10:47
 */
@Data
@Accessors(chain = true)
public class LogStatisticsItem {
    private Integer number;
    private Date date;

    public static LogStatisticsItem init() {
        return new LogStatisticsItem().setNumber(0);
    }
}
