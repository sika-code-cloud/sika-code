package com.sika.code.demo.infrastructure.business.user.pojo.query;

import com.sika.code.core.base.pojo.query.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * OrderQuery
 *
 * @author : daiqi
 * @date : 2023-08-25
 */
@Data
public class OrderQuery extends PageQuery<Long> {

    /** 银盛订单号 */
//    @Sharding
    private String orderNo;
    /** 创建时间 */
    private LocalDateTime createTime;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
