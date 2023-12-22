package com.sika.code.demo.sharding.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 加强hint 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:18
 */
@Getter
@Setter
@TableName("sika_hint_plus")
public class HintPlusPO extends BasePO {
    private String hintPlusNo;
}
