package com.sika.code.batch.test.animal;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;

/**
 * @author daiqi
 * @create 2019-09-12 23:09
 */
@Data
public class AnimalDTO extends BaseStandardDTO {
    private String name;
    private String color;
}
