package com.zyd.blog.business.dto;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2020-09-25 13:12
 */
@Data
@Accessors(chain = true)
public class BatchArticleLookDTO {
    private Integer addNumber;
    private Long articleId;

    public Integer getAddNumber() {
        if (this.addNumber == null) {
            return 0;
        }
        return this.addNumber;
    }

    public Integer getAddNumberNullToRandom() {
        if (this.addNumber == null) {
            return RandomUtil.randomInt(5, 10);
        }
        return this.addNumber;
    }
}
