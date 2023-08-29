package com.sika.code.db.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 批量更新方法实现，条件为主键，选择性更新
 */
@Slf4j
public class UpdateBatchMethod extends AbstractMethod {
    public UpdateBatchMethod(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        StringBuilder sqlResult = new StringBuilder();
        StringBuilder updateTableBuilder = new StringBuilder();
        sqlResult.append("<script>\n");
        updateTableBuilder.append("UPDATE ").append(tableInfo.getTableName()).append(" SET ");
        // 构建caseWhen的语句
        StringBuilder caseWhenSqlBuild = buildCaseWhen(tableInfo);
        // 构建where的语句
        StringBuilder whereSqlBuilder = buildWhereSql(tableInfo);
        sqlResult.append(updateTableBuilder).append(caseWhenSqlBuild).append(whereSqlBuilder);
        sqlResult.append("</script>");
        if (logger.isDebugEnabled()) {
            logger.debug("组装后的sql为:" + sqlResult);
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult.toString(), modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, "updateBatchCaseWhen", sqlSource);
    }

    /**
     * <p>
     * 构建caseWhen的跟新语句
     * </p >
     *
     * @param tableInfo
     * @return java.lang.StringBuilder
     * @author sikadai
     * @since 2022/8/24 18:42
     */
    private StringBuilder buildCaseWhen(TableInfo tableInfo) {
        StringBuilder caseWhenSqlBuild = new StringBuilder();
        int count = 0;
        int fieldSize = tableInfo.getFieldList().size();
        for (TableFieldInfo fieldInfo : tableInfo.getFieldList()) {
            count++;
            caseWhenSqlBuild.append(fieldInfo.getColumn()).append(" = CASE ").append(tableInfo.getKeyColumn()).append("\n");
            caseWhenSqlBuild.append("<foreach collection=\"list\" item=\"item\" index=\"index\">\n");
            caseWhenSqlBuild.append("<choose>\n");
            caseWhenSqlBuild.append("<when test=\"item.").append(fieldInfo.getProperty()).append(" != null\">\n");
            caseWhenSqlBuild.append("WHEN #{item.").append(tableInfo.getKeyProperty()).append("} THEN #{item.").append(fieldInfo.getProperty()).append("}");
            caseWhenSqlBuild.append("</when>\n");
            caseWhenSqlBuild.append("<otherwise>\n");
            caseWhenSqlBuild.append("WHEN #{item.").append(tableInfo.getKeyProperty()).append("} THEN ").append(fieldInfo.getColumn());
            caseWhenSqlBuild.append("</otherwise>\n");
            caseWhenSqlBuild.append("</choose>\n");
            caseWhenSqlBuild.append("</foreach>\n");
            caseWhenSqlBuild.append("END");
            if (count < fieldSize) {
                caseWhenSqlBuild.append(",\n");
            } else {
                caseWhenSqlBuild.append("\n");
            }
        }
        return caseWhenSqlBuild;
    }

    /**
     * <p>
     * 构建where的批量更新
     * </p >
     *
     * @param tableInfo
     * @return java.lang.StringBuilder
     * @author sikadai
     * @since 2022/8/24 18:43
     */
    private StringBuilder buildWhereSql(TableInfo tableInfo) {
        StringBuilder whereSqlBuilder = new StringBuilder();
        whereSqlBuilder.append(sqlWhereEntityWrapper(true, tableInfo));
        return whereSqlBuilder;
    }
}
