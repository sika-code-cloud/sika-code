package com.sika.code.demo.sharding.pojo.query;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 二次hash 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:18
 */
@Getter
@Setter
public class TwiceHashCmpxQuery extends BaseQuery {
    private String twiceHashCmpxNo;
    private String twiceHashCmpxRNo;
}
