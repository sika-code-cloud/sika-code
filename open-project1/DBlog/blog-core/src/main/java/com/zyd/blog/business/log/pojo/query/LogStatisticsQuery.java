package com.zyd.blog.business.log.pojo.query;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zyd.blog.business.log.enums.QueryTypeEnum;
import com.zyd.blog.persistence.beans.SysLog;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 访问日志统计查询对象
 *
 * @author daiqi
 * @create 2020-09-18 10:42
 */
@Data
@Accessors(chain = true)
public class LogStatisticsQuery extends SysLog {
    private String pointDate;
    private Date beginDate;
    private Date endDate;
    private QueryTypeEnum queryTypeEnum;

    public Date getPointDateNullToNow() {
        if (StrUtil.isEmpty(this.pointDate)) {
            return DateUtil.date();
        }
        return DateUtil.parseDate(this.pointDate);
    }
    public LogStatisticsQuery build() {
        if (this.beginDate == null) {
            this.beginDate = new Date();
        }
        if (this.endDate == null) {
            this.endDate = new Date();
        }
        this.beginDate = DateUtil.beginOfDay(beginDate);
        this.endDate = DateUtil.endOfDay(endDate);
        return this;
    }


}
