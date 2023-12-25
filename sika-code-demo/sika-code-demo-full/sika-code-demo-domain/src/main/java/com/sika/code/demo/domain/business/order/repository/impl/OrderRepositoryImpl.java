package com.sika.code.demo.domain.business.order.repository.impl;

import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import com.sika.code.demo.domain.business.order.repository.OrderRepository;
import com.sika.code.demo.infrastructure.db.business.order.mapper.OrderMapper;
import com.sika.code.demo.infrastructure.db.business.order.po.OrderPO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:42
 */
@Repository
public class OrderRepositoryImpl extends BaseRepositoryMyBatisPlusImpl<OrderPO, Long, OrderMapper>
    implements OrderRepository {

}

