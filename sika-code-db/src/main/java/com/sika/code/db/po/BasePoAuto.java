
package com.sika.code.db.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sika.code.core.base.pojo.po.BasePO;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePoAuto<PRIMARY extends Serializable> extends BasePO<PRIMARY> {
    @TableId(
            type = IdType.AUTO
    )
    protected PRIMARY id;
}
