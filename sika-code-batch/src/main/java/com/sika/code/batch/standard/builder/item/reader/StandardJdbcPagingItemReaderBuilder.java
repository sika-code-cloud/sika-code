package com.sika.code.batch.standard.builder.item.reader;

import com.google.common.collect.Maps;
import com.sika.code.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.batch.core.factory.BatchFactory;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemReaderBean;
import com.sika.code.batch.standard.bean.reader.JdbcReaderBean;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
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
public class StandardJdbcPagingItemReaderBuilder implements BaseItemReaderBuilder<Map<String, Object>> {

    @Override
    public ItemReader<Map<String, Object>> build(BatchBean batchBean) {
        ItemReaderBean<?> itemReader = batchBean.getItemReaderBean();
        JdbcReaderBean jdbcReaderBean = (JdbcReaderBean) itemReader.buildBeanObj();
        DataSource dataSource = BatchFactory.getDataSource(jdbcReaderBean);
        PagingQueryProvider queryProvider = null;
        try {
            SqlPagingQueryProviderFactoryBean pagingQueryProviderFactoryBean = queryProvider(jdbcReaderBean);
            pagingQueryProviderFactoryBean.setDataSource(dataSource);
            queryProvider = pagingQueryProviderFactoryBean.getObject();

            JdbcPagingItemReaderBuilder<Map<String, Object>> jdbcPagingItemReaderBuilder = new JdbcPagingItemReaderBuilder<>();
            JdbcPagingItemReader<Map<String, Object>> jdbcPagingItemReader = jdbcPagingItemReaderBuilder
                    .saveState(false)
                    .dataSource(dataSource)
                    .queryProvider(queryProvider)
                    .rowMapper(new StandardJdbcCursorRowMapper(jdbcReaderBean))
                    .pageSize(jdbcReaderBean.getPageSize())
                    .build();
            jdbcPagingItemReader.afterPropertiesSet();
            return jdbcPagingItemReader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SqlPagingQueryProviderFactoryBean queryProvider(JdbcReaderBean jdbcReaderBean) {
        SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();
        provider.setSelectClause(jdbcReaderBean.getSelectSql());
        provider.setFromClause(jdbcReaderBean.getFromSql());
        provider.setWhereClause(jdbcReaderBean.getWhereSql());
        Map<String, Order> orderMap = Maps.newLinkedHashMap();
        for (Map.Entry<String, String> entry : jdbcReaderBean.getSortKeyType().entrySet()) {
            orderMap.put(entry.getKey(), Order.valueOf(entry.getValue()));
        }
        provider.setSortKeys(orderMap);
        return provider;
    }

    public static class StandardJdbcCursorRowMapper implements RowMapper<Map<String, Object>> {
        private final JdbcReaderBean jdbcReaderBean;

        public StandardJdbcCursorRowMapper(JdbcReaderBean jdbcReaderBean) {
            this.jdbcReaderBean = jdbcReaderBean;
        }

        @Override
        public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : jdbcReaderBean.getResultSetKey().entrySet()) {
                map.put(entry.getKey(), resultSet.getObject(entry.getValue()));
            }
            return map;
        }
    }
}
