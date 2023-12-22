package com.ruoyi.generator.controller.pg;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.ruoyi.generator.domain.IndexColumn;
import common.utils.StringUtils;
import com.ruoyi.generator.controller.GentableProvider;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.util.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * parse pg create table statement
 */
public class PgGentableProvider implements GentableProvider {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<GenTable> getGenTable(String sql, String ignorePrefix) {

        sql = sql.replace("COLLATE \"pg_catalog\".\"default\"", "")
                .replace("::character varying","")
                .replace("COLLATE pg_catalog.\"default\"","");

        sql = sql.replaceAll("ALTER.*[\r\n]?.*OWNER.*", "");


        List<GenTable> genTables = new ArrayList<>();

        try {

            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.postgresql);
            //key is tablename
            Map<String, Map<String, String>> tableColumnCommentMap = new HashMap<>();

            for (SQLStatement sqlStatement : sqlStatements) {

                if (sqlStatement instanceof SQLCommentStatement) {

                    SQLCommentStatement sqlCommentStatement = (SQLCommentStatement) sqlStatement;
                    SQLExprTableSource on = sqlCommentStatement.getOn();

                    String tableName = on.getSchema();
                    String columnName = on.getName().getSimpleName();
                    String comment = sqlCommentStatement.getComment().toString().replace("'", "");


                    if (!tableColumnCommentMap.containsKey(tableName)) {

                        Map<String, String> colMap = new HashMap<>();
                        colMap.put(columnName, comment);
                        tableColumnCommentMap.put(tableName, colMap);

                    } else {

                        tableColumnCommentMap.get(tableName).put(columnName, comment);
                    }
                }
            }

            for (SQLStatement sqlStatement : sqlStatements) {


                if (sqlStatement instanceof SQLCreateTableStatement) {

                    List<GenTableColumn> columns = new ArrayList<>();

                    SQLCreateTableStatement createTableStatement = (SQLCreateTableStatement) sqlStatement;

                    List<SQLTableElement> tableElementList = createTableStatement.getTableElementList();

                    String tableName = createTableStatement.getTableName().replace("\"","");
                    if(ignorePrefix==null){
                        ignorePrefix="";
                    }

                    GenTable genTable = new GenTable();
                    genTable.setClassName(GenUtils.convertClassName(tableName.replaceFirst(ignorePrefix,"")));
                    genTable.setBusinessName(GenUtils.getBusinessName(tableName.replaceFirst(ignorePrefix,"")));
                    genTable.setFunctionName(GenUtils.replaceText(tableName.replaceFirst(ignorePrefix,"")));
                    genTable.setTableName(tableName);


                    Map<String, String> colCommentMap = tableColumnCommentMap.get(tableName);

                    for (SQLTableElement sqlTableElement : tableElementList) {

                        //表字段定义
                        if (sqlTableElement instanceof SQLColumnDefinition) {

                            SQLColumnDefinition columnDefinition = (SQLColumnDefinition) sqlTableElement;
                            GenTableColumn column = new GenTableColumn();
                            column.setColumnName(columnDefinition.getColumnName().replace("\"",""));
                            column.setColumnType(columnDefinition.getDataType().getName());

                            if (colCommentMap != null) {
                                String comment = colCommentMap.get(column.getColumnName());
                                if (StringUtils.isNotEmpty(comment)) {
                                    column.setColumnComment(comment);
                                }
                            }

                            columns.add(column);

                            GenUtils.initColumnField(column, genTable);
                            genTable.setColumns(columns);

                            if(isPrimary(columnDefinition) && genTable.getPkColumn()==null){
                                genTable.setPkColumn(column);
                            }

                            continue;
                        }
                    }

                    Map<String, GenTableColumn> columnNameMap = columns.stream()
                            .collect(Collectors.toMap(GenTableColumn::getColumnName, Function.identity()));


                    //loop again, find the union primary keys
                    for (SQLTableElement sqlTableElement : tableElementList) {

                        //primaryKey columns
                        if (sqlTableElement instanceof SQLPrimaryKeyImpl) {

                            SQLPrimaryKeyImpl sqlPrimaryKey = (SQLPrimaryKeyImpl) sqlTableElement;

                            List<SQLSelectOrderByItem> sqlSelectOrderByItems = sqlPrimaryKey.getIndexDefinition().getColumns();

                            for (SQLSelectOrderByItem sqlSelectOrderByItem : sqlSelectOrderByItems) {

                                String columnName = ((SQLIdentifierExpr) sqlSelectOrderByItem.getExpr()).getName().replace("\"","");

                                GenTableColumn genTableColumn = columnNameMap.get(columnName);
                                if(genTableColumn!=null && genTable.getPkColumn()==null){
                                    genTable.setPkColumn(genTableColumn);
                                }
                            }
                        }
                    }

                    genTables.add(genTable);
                }
            }




            Map<String, GenTable> getTableMap = genTables.stream()
                    .collect(Collectors.toMap(GenTable::getTableName, Function.identity()));

            for (SQLStatement sqlStatement : sqlStatements) {

                if(sqlStatement instanceof SQLCreateIndexStatement){

                    SQLCreateIndexStatement sqlCreateIndexStatement=(SQLCreateIndexStatement) sqlStatement;
                    String tableName = sqlCreateIndexStatement.getTableName().replace("\"","");

                    Map<String, GenTableColumn> columnNameMap = getTableMap.get(tableName).getColumns().stream()
                            .collect(Collectors.toMap(GenTableColumn::getColumnName, Function.identity()));


                    List<SQLSelectOrderByItem> indexColumns = sqlCreateIndexStatement.getColumns();

                    List<GenTableColumn> genTableColumns=new ArrayList<>();

                    for (SQLSelectOrderByItem column : indexColumns) {
                        String columnName = column.getExpr().toString();
                        GenTableColumn genTableColumn = columnNameMap.get(columnName.replace("\"",""));
                        genTableColumns.add(genTableColumn);

                    }

                    IndexColumn indexColumn=new IndexColumn(genTableColumns);
                    getTableMap.get(tableName).getIndexColumns().add(indexColumn);
                }
            }

        } catch (Exception ex) {
            logger.error("解析sql出错", ex);
            throw new RuntimeException(ex);
        }

        return genTables;
    }


    private boolean isPrimary(SQLColumnDefinition sqlColumnDefinition) {

        List<SQLColumnConstraint> constraints = sqlColumnDefinition.getConstraints();

        if (constraints == null || constraints.size() == 0) {
            return false;
        }

        for (SQLColumnConstraint constraint : constraints) {
            if (constraint instanceof SQLColumnPrimaryKey) {
                return true;
            }
        }

        return false;
    }


}
