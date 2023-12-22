package com.sika.sharding.sphere.plus.sql;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.sika.sharding.sphere.plus.GenerateSqlUtil;
import com.sika.sharding.sphere.plus.SqlUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * BaseDateGenerateSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public abstract class BaseGenerateDateSqlHandler extends BaseGenerateSqlHandler {
    @Override
    public void generate(GenerateSqlUtil.GenerateSQLParam sqlParam) {
        try {
            int dbTotal = sqlParam.getDbTotal();
            String fileNamePrefix = sqlParam.getFileNamePrefix();
            boolean fileSplitByDbIndex = sqlParam.isFileSplitByDbIndex();
            String sourceFileName = sqlParam.getSourceFileName();
            StringBuilder fileName = new StringBuilder(fileNamePrefix);
            StringBuilder result = new StringBuilder();
            for (int i = sqlParam.getDbStart(); i < sqlParam.getDbStart() + dbTotal; ++i) {
                LocalDate yearStartDay = LocalDate.of(i, 1, 1);
                LocalDate yearEndDay = LocalDate.of(i, 12, 31);
                if (fileSplitByDbIndex) {
                    result.append(replaceTableSql(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(yearStartDay.getYear()).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSql(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~")
                    .append(sqlParam.getDbStart() + dbTotal - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                    StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String replaceTableSql(LocalDate startLocalDate, LocalDate endLocalDate, String sourceFileName,
        String format) {
        String sql = SqlUtils.getResourceAsString(GenerateSqlUtil.class, sourceFileName);
        StringBuilder sb = new StringBuilder();
        LocalDate currentDay = startLocalDate;
        while (!currentDay.isAfter(endLocalDate)) {
            sb.append(sql.replaceAll(DB_INDEX_KEY, String.valueOf(currentDay.getYear()))
                    .replaceAll(TABLE_INDEX_KEY, LocalDateTimeUtil.format(currentDay, format)).replaceAll("\"", ""))
                .append("\n");
            currentDay = nextLocalDate(currentDay);
        }
        return sb.toString();
    }

    protected abstract LocalDate nextLocalDate(LocalDate currentLocalDate);
}
