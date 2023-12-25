package com.sika.code.demo.sharding.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sika_twice_hash_cmpx")
public class TwiceHashCmpxPO extends BasePO {
    private String twiceHashCmpxNo;
    private String twiceHashCmpxRNo;
}
