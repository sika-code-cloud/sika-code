package com.sika.code.batch.animal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import lombok.Data;

/**
 * @author daiqi
 * @create 2019-09-12 0:10
 */
@Data
@TableName(value = "animal")
public class AnimalEntity extends BaseStandardEntity {
    private String name;
    private String color;

}
