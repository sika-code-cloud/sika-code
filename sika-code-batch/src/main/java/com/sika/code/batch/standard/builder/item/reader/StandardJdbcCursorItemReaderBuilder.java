package com.sika.code.batch.standard.builder.item.reader;

import cn.hutool.core.util.StrUtil;
import com.sika.code.core.base.pojo.domain.factory.MetaSpringUtil;
import com.sika.code.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/5 7:56
 */
@Setter
public class StandardJdbcCursorItemReaderBuilder implements BaseItemReaderBuilder<Map<String, Object>> {
    private DataSource dataSource;
    private static final String QUERY_SQL_TEMPLATE = "select {} from {} where {} order by id desc";

    @Override
    public ItemReader<Map<String, Object>> build(BatchBean batchBean) {
        String sql = StrUtil.format(QUERY_SQL_TEMPLATE, "username,nickname,avatar", "lf_third_oauth_user", "1=1");
        dataSource = MetaSpringUtil.getBean(DataSource.class);
        JdbcCursorItemReaderBuilder<Map<String, Object>> jdbcCursorItemReaderBuilder = new JdbcCursorItemReaderBuilder<>();
        return jdbcCursorItemReaderBuilder.dataSource(dataSource)
                .rowMapper(new StandardJdbcCursorRowMapper())
                .fetchSize(5000)
                .sql(sql)
                .saveState(false)
                .build();
    }

    public static class StandardJdbcCursorRowMapper implements RowMapper<Map<String, Object>> {
        @Override
        public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("username", resultSet.getObject("username"));
            map.put("nickname", resultSet.getObject("nickname"));
            map.put("avatar", resultSet.getObject("avatar"));
            return map;
        }
    }
}
