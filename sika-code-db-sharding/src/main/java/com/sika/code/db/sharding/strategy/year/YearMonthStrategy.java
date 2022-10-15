package com.sika.code.db.sharding.strategy.year;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import com.sika.code.db.sharding.util.ShardingUtil;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 年库月表分库分表策略
 * </p >
 *
 * @author sikadai
 * @return
 * @since 2022/9/16 16:18
 */
@Component
public class YearMonthStrategy extends BaseYearXxStrategy {

    @Override
    public String returnTableName(String tableName, Object shardingTableValue) {
        String tableTemValue = ShardingUtil.formatDate(shardingTableValue, YYYY_MM);
        return CharSequenceUtil.join(StrPool.UNDERLINE, tableName, tableTemValue);
    }
}
