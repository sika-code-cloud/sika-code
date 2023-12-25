package com.sika.code.demo.domain.business.order.repository;

import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;
import com.sika.code.demo.infrastructure.db.business.order.mapper.OrderMapper;
import com.sika.code.demo.infrastructure.db.business.order.po.OrderPO;

/**
 * <p>
 * 用户表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:41
 */
public interface OrderRepository extends BaseBizRepository<OrderPO, OrderMapper> {

}
