package com.ruoyi.generator.controller.mysql;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.ruoyi.generator.controller.GentableProvider;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.util.GenUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * parse mysql create table statement
 */
public class MysqlGentableProvider implements GentableProvider {

    @Override
    public List<GenTable> getGenTable(String sql, String ignorePrefix)  {

        List<GenTable> genTables=new ArrayList<>();

        try {

            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
            //key is tablename
            Map<String, Map<String,String >>  tableColumnCommentMap=new HashMap<>();

            for (SQLStatement sqlStatement : sqlStatements) {

                if(sqlStatement instanceof SQLCommentStatement){

                    SQLCommentStatement sqlCommentStatement=(SQLCommentStatement) sqlStatement;
                    SQLExprTableSource on = sqlCommentStatement.getOn();

                    String tableName = on.getSchema();
                    String columnName = on.getName().getSimpleName();
                    String comment = sqlCommentStatement.getComment().toString().replace("'","");


                    if(!tableColumnCommentMap.containsKey(tableName)){

                        Map<String,String> colMap=new HashMap<>();
                        colMap.put(columnName,comment);
                        tableColumnCommentMap.put(tableName,colMap);

                    }else {

                        tableColumnCommentMap.get(tableName).put(columnName,comment);
                    }
                }
            }


            for (SQLStatement sqlStatement : sqlStatements) {

                if(sqlStatement instanceof SQLCreateTableStatement){

                    SQLCreateTableStatement createTableStatement=(SQLCreateTableStatement) sqlStatement;
                    GenTable genTable=new GenTable();
                    genTable.setClassName(GenUtils.convertClassName(createTableStatement.getTableName()));
                    genTable.setBusinessName(GenUtils.getBusinessName(createTableStatement.getTableName()));
                    genTable.setFunctionName(GenUtils.replaceText(createTableStatement.getTableName()));
                    genTable.setTableName(genTable.getClassName());

                    List<SQLColumnDefinition> columnDefinitions = createTableStatement.getColumnDefinitions();

                    List<GenTableColumn> columns=new ArrayList<>();


                    Map<String, String> colCommentMap = tableColumnCommentMap.get(createTableStatement.getTableName());

                    for (SQLColumnDefinition columnDefinition : columnDefinitions) {

                        GenTableColumn column=new GenTableColumn();
                        column.setColumnName(columnDefinition.getColumnName());
                        column.setColumnType(columnDefinition.getDataType().getName());

                        if(colCommentMap!=null){
                            String comment = colCommentMap.get(column.getColumnName());
                            if(StringUtils.isNotEmpty(comment)){
                                column.setColumnComment(comment);
                            }
                        }

                        GenUtils.initColumnField(column, genTable);

                        column.setIsPk(isPrimary(columnDefinition)?"1":"0");
                        columns.add(column);
                    }

                    genTable.setColumns(columns);
                    genTables.add(genTable);
                }
            }

        }catch (Exception ex){
            throw ex;
        }

        return genTables;
    }



    private boolean isPrimary(SQLColumnDefinition sqlColumnDefinition){

        List<SQLColumnConstraint> constraints = sqlColumnDefinition.getConstraints();

        if(constraints==null ||constraints.size()==0){
            return false;
        }

        for (SQLColumnConstraint constraint : constraints) {
            if(constraint instanceof SQLColumnPrimaryKey){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        String stmt="create table sys_dept (\n" +
                "  dept_id           bigserial      not null primary key  ,\n" +
                "  parent_id         bigint      default 0   ,\n" +
                "  ancestors         varchar(50)     default ''  ,\n" +
                "  dept_name         varchar(30)     default ''  ,\n" +
                "  order_num         int         default 0       ,\n" +
                "  leader            varchar     default null       ,\n" +
                "  phone             varchar(11)     default null   ,\n" +
                "  email             varchar(50)     default null   ,\n" +
                "  status            char(1)         default '0'     ,\n" +
                "  del_flag          char(1)         default '0'     ,\n" +
                "  create_by         varchar(64)     default ''      ,\n" +
                "  create_time \t    time                             ,\n" +
                "  update_by         varchar(64)     default ''   ,\n" +
                "  update_time       time                                   \n" +
                ") ;\n" +
                "\n" +
                "\n" +
                "comment on column sys_dept.dept_id is '部门Id';\n";


        MysqlGentableProvider pgGentableProvider=new MysqlGentableProvider();
        List<GenTable> genTable = pgGentableProvider.getGenTable(stmt, "");


        System.out.println("genTable = " + genTable);

    }

}
