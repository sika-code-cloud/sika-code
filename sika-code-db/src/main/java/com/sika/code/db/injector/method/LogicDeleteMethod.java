package com.sika.code.db.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import static com.baomidou.mybatisplus.core.enums.SqlMethod.LOGIC_DELETE;

/**
 * 批量更新方法实现，条件为主键，选择性更新
 */
@Slf4j
public class LogicDeleteMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String set = "SET data_version = " + tableInfo.getKeyColumn() + ", is_deleted = 1, version = version + 1";
        String sql = String.format(LOGIC_DELETE.getSql(), tableInfo.getTableName(), set,
                sqlWhereEntityWrapper(true, tableInfo),
                sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, "logicDelete", sqlSource);
    }

}
