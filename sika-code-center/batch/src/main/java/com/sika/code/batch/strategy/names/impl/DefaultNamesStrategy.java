package com.sika.code.batch.strategy.names.impl;

import com.sika.code.basic.util.Assert;
import com.sika.code.batch.strategy.names.NamesStrategy;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 默认的name策略，需要用户指定
 *
 * @author daiqi
 * @create 2019-12-03 23:24
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class DefaultNamesStrategy implements NamesStrategy {
    private List<String> names;

    @Override
    public DefaultNamesStrategy build() {
        Assert.verifyListEmpty(names, "names");
        return this;
    }

}
