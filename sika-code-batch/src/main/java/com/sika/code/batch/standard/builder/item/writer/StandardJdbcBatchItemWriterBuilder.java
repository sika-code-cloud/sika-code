package com.sika.code.batch.standard.builder.item.writer;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.core.factory.BatchFactory;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.JdbcWriterBean;
import com.sika.code.batch.standard.item.writer.JdbcBatchItemWriterSupport;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.support.ColumnMapItemPreparedStatementSetter;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 19:05
 */
@Data
public class StandardJdbcBatchItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {
    private static final String INSERT_SQL_TEMPLATE = "insert into {} ({}) values ({})";
    private static final String DOUBT = "?";

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        JdbcWriterBean jdbcWriterBean = (JdbcWriterBean) itemWriterBean.buildBeanObj();
        DataSource dataSource = BatchFactory.getDataSource(jdbcWriterBean);
        // 通过Jdbc写入到数据库中

        // 获取标准的处理类
        JdbcBatchItemWriterSupport writer = new JdbcBatchItemWriterSupport()
                .setDataBuilder(jdbcWriterBean.getBaseWriterDataBuilder());
        writer.setDataSource(dataSource);
        // setItemSqlParameterSourceProvider 表示将实体类中的属性和占位符一一映射
        writer.setItemPreparedStatementSetter(new ColumnMapItemPreparedStatementSetter());
        // 设置要执行批处理的SQL语句。其中占位符的写法是 ?
        writer.setSql(buildSql(batchBean, jdbcWriterBean));
        writer.afterPropertiesSet();
        return writer;
    }

    protected String buildSql(BatchBean batchBean, JdbcWriterBean jdbcWriterBean) {
        Map<String, String> processorWriterMapper = BatchFactory.buildProcessorWriterMapper(batchBean.readerProcessorMapper(), jdbcWriterBean.getProcessorWriterMapper());

        StringBuilder tableNamesStr = new StringBuilder(128);
        StringBuilder placeholdersStr = new StringBuilder(64);
        int count = 0;
        for (Map.Entry<String, String> processorWriter : processorWriterMapper.entrySet()) {
            tableNamesStr.append(processorWriter.getValue());
            placeholdersStr.append(DOUBT);
            if (count < processorWriterMapper.size() - 1) {
                tableNamesStr.append(StrPool.COMMA);
                placeholdersStr.append(StrPool.COMMA);
            }
            count++;
        }
        return StrUtil.format(INSERT_SQL_TEMPLATE, jdbcWriterBean.getTableName(), tableNamesStr, placeholdersStr);
    }
}
