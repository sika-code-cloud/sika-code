package com.sika.code.batch.partitioner;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 分区批处理
 *
 * @author daiqi
 * @create 2019-09-17 0:17
 */
@Data
public class DBPartitioner implements Partitioner {
    private static final String MIN_RECORD = "min_record";
    private static final String MAX_RECORD = "max_record";
    private static final String MIN_SELECT_PATTERN = "select min({0} from {1})";
    private static final String MAX_SELECT_PATTERN = "select max({0} from {1})";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private String table;
    private String column;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> retMap = Maps.newHashMap();
        Integer min = jdbcTemplate.queryForObject(MessageFormat.format(MIN_SELECT_PATTERN, new Object[]{column, table}), Integer.class);
        Integer max = jdbcTemplate.queryForObject(MessageFormat.format(MAX_SELECT_PATTERN, new Object[]{column, table}), Integer.class);
        int targetSize = (max - min) / gridSize + 1;
        int number = 0;
        int start = min;
        int end = start + targetSize - 1;
        while (start <= end) {
            ExecutionContext context = new ExecutionContext();
            if (end >= max ) {
                end = max;
            }
            context.putInt(MIN_RECORD, start);
            context.putInt(MAX_RECORD, end);
            start += targetSize;
            end += targetSize;
            retMap.put("partition" + (number ++), context);
        }

        return retMap;
    }
}
