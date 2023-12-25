package com.ruoyi.generator.domain;

import common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author lancelot
 * @version 1.0
 * @date 2023/1/9 上午10:22
 */
public class IndexColumn {

    private String columnName;

    private List<GenTableColumn> genTableColumns=new ArrayList<>();

    public IndexColumn(List<GenTableColumn> genTableColumns) {

        this.genTableColumns = genTableColumns;
        StringJoiner stringJoiner=new StringJoiner("And");

        for (GenTableColumn genTableColumn : genTableColumns) {

            String capitalize = StringUtils.capitalize(StringUtils.toCamelCase(genTableColumn.getColumnName().replace("\"","")));
            stringJoiner.add(capitalize);
        }

        this.columnName=stringJoiner.toString();

    }

    public List<GenTableColumn> getGenTableColumns() {
        return genTableColumns;
    }

    public void setGenTableColumns(List<GenTableColumn> genTableColumns) {
        this.genTableColumns = genTableColumns;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
