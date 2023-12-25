package com.sika.sharding.sphere.plus.sql;

import com.sika.sharding.sphere.plus.GenerateSqlUtil;
import com.sika.sharding.sphere.plus.SqlUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * BaseGenerateXXDBYYTableSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public abstract class BaseGenerateXxYySqlHandler extends BaseGenerateSqlHandler {
    @Override
    public void generate(GenerateSqlUtil.GenerateSQLParam sqlParam) {
        try {
            int dbTotal = sqlParam.getDbTotal();
            int tableNumPerDb = sqlParam.getPerDbTableNum();
            boolean fileSplitByDbIndex = sqlParam.isFileSplitByDbIndex();
            StringBuilder result = new StringBuilder();
            for (int dbIndex = sqlParam.getDbStart(); dbIndex < getDbTotal(sqlParam.getDbStart(), dbTotal); ++dbIndex) {
                int startTableIndex = getTableStartIndex(tableNumPerDb, dbIndex);
                int endTableIndex = getTableEndIndex(tableNumPerDb, dbIndex);
                result = appendOrWriteResult(sqlParam, result, dbIndex, startTableIndex, endTableIndex);
            }
            if (!fileSplitByDbIndex) {
                writeUnFileSplit(sqlParam, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int getDbTotal(int dbStart, int dbTotal) {
        return dbTotal;
    }

    protected abstract int getTableStartIndex(int tableNumPerDb, int dbIndex);

    protected abstract int getTableEndIndex(int tableNumPerDb, int dbIndex);

    protected String replaceTableSql(int dbIndex, int tableStartIndex, int tableEndIndex, String sourceFileName) {
        String sql = SqlUtils.getResourceAsString(GenerateSqlUtil.class, sourceFileName);
        StringBuilder sb = new StringBuilder();
        for (int j = tableStartIndex; j <= tableEndIndex; j++) {
            String dbIndexStr = String.valueOf(dbIndex);
            if (dbIndex < 10) {
                dbIndexStr = "0" + dbIndexStr;
            }
            sb.append(sql.replaceAll(DB_INDEX_KEY, dbIndexStr).replaceAll(TABLE_INDEX_KEY, String.valueOf(j))
                .replaceAll("\"", "")).append("\n");
        }
        return sb.toString();
    }

    protected StringBuilder appendOrWriteResult(GenerateSqlUtil.GenerateSQLParam sqlParam, StringBuilder result,
        int dbIndex, int startTableIndex, int endTableIndex) throws IOException {
        if (sqlParam.isFileSplitByDbIndex()) {
            result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sqlParam.getSourceFileName()));
            String fileName =
                sqlParam.getFileNamePrefix() + "_" + dbIndex + "_" + startTableIndex + "~" + endTableIndex + ".sql";
            FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                StandardCharsets.UTF_8);
            result = new StringBuilder();
        } else {
            result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sqlParam.getSourceFileName()));
        }
        return result;
    }

    protected void writeUnFileSplit(GenerateSqlUtil.GenerateSQLParam sqlParam, StringBuilder result)
        throws IOException {
        StringBuilder fileName = new StringBuilder(sqlParam.getFileNamePrefix());
        fileName.append("_").append(sqlParam.getDbStart()).append("~")
            .append(sqlParam.getDbStart() + sqlParam.getDbTotal() - 1).append(".sql");
        FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
            StandardCharsets.UTF_8);
    }

}
