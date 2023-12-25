package com.sika.sharding.sphere.plus;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.sika.sharding.sphere.plus.sql.GenerateTableIncrSqlHandler;
import com.sika.sharding.sphere.plus.sql.GenerateTableLoopSqlHandler;
import com.sika.sharding.sphere.plus.sql.GenerateYearDaySqlHandler;
import com.sika.sharding.sphere.plus.sql.GenerateYearMonthSqlHandler;
import com.sika.sharding.sphere.plus.sql.GenerateYearWeekSqlHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/9 20:46
 */
public class GenerateSqlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateSqlUtil.class);

    public static void main(String[] args) {
        GenerateSQLParam sqlParam =
                new GenerateSQLParam().setDbTotal(32).setPerDbTableNum(8).setTargetDirectory("D://sika//")
                        .setFileNamePrefix("twice_hash").setFileSplitByDbIndex(false).setSourceFileName("twice_hash.sql");
        new GenerateTableIncrSqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbTotal(32).setPerDbTableNum(8).setTargetDirectory("D://sika//")
                        .setFileNamePrefix("hint_plus").setFileSplitByDbIndex(false).setSourceFileName("hint_plus.sql");
        new GenerateTableLoopSqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbStart(2020).setDbTotal(5).setDateFormat("MMdd").setTargetDirectory("D://sika//")
                        .setFileNamePrefix("year_day").setFileSplitByDbIndex(false).setSourceFileName("year_day.sql");
        new GenerateYearDaySqlHandler().generate(sqlParam);
        sqlParam =
                new GenerateSQLParam().setDbStart(2020).setDbTotal(5).setPerDbTableNum(53).setTargetDirectory("D://sika//")
                        .setFileNamePrefix("year_week").setFileSplitByDbIndex(false).setSourceFileName("year_week.sql");
         new GenerateYearWeekSqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbStart(2020).setDbTotal(5).setDateFormat("MM").setTargetDirectory("D://sika//")
                        .setFileNamePrefix("year_month").setFileSplitByDbIndex(false).setSourceFileName("year_month.sql");
        new GenerateYearMonthSqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbStart(2020).setDbTotal(5).setDateFormat("MMdd").setTargetDirectory("D://sika//")
                        .setFileNamePrefix("year_day_drop").setFileSplitByDbIndex(false).setSourceFileName("dropTemplate.sql");
        new GenerateYearDaySqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbStart(2020).setDbTotal(5).setDateFormat("MM").setTargetDirectory("D://sika//")
                        .setFileNamePrefix("year_month_drop").setFileSplitByDbIndex(false).setSourceFileName("dropTemplate.sql");
        new GenerateYearMonthSqlHandler().generate(sqlParam);

        sqlParam =
                new GenerateSQLParam().setDbTotal(32).setPerDbTableNum(8).setTargetDirectory("D://sika//")
                        .setFileNamePrefix("twice_hash_cmpx").setFileSplitByDbIndex(false).setSourceFileName("twice_hash_cmpx.sql");
        new GenerateTableIncrSqlHandler().generate(sqlParam);
    }


    private static void generateYearDbWeekTableSqlFile(GenerateSQLParam sqlParam) {
        try {
            int dbTotal = sqlParam.getDbTotal();
            int tableTotal = sqlParam.getPerDbTableNum();
            String fileNamePrefix = sqlParam.getFileNamePrefix();
            boolean fileSplitByDbIndex = sqlParam.isFileSplitByDbIndex();
            String sourceFileName = sqlParam.getSourceFileName();
            StringBuilder fileName = new StringBuilder(fileNamePrefix);
            StringBuilder result = new StringBuilder();
            for (int dbIndex = sqlParam.getDbStart(); dbIndex < sqlParam.getDbStart() + dbTotal; ++dbIndex) {
                int startTableIndex = 1;
                if (fileSplitByDbIndex) {
                    result.append(replaceTableSql(dbIndex, startTableIndex, tableTotal, sourceFileName));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(sqlParam.getDbStart()).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                            StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSql(dbIndex, startTableIndex, tableTotal, sourceFileName));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~").append(sqlParam.getDbStart() + dbTotal - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void generateYearDbMonthTableSqlFile(GenerateSQLParam sqlParam) {
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
                    result.append(replaceTableSqlForMonth(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(yearStartDay.getYear()).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                            StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSqlForMonth(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~").append(sqlParam.getDbStart() + dbTotal - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateYearDbDayTableSqlFile(GenerateSQLParam sqlParam) {
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
                    result.append(replaceTableSqlForDay(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(yearStartDay.getYear()).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                            StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSqlForDay(yearStartDay, yearEndDay, sourceFileName, sqlParam.getDateFormat()));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~").append(sqlParam.getDbStart() + dbTotal - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateXXDbYYTableSqlFile(GenerateSQLParam sqlParam) {
        try {
            int dbTotal = sqlParam.getDbTotal();
            int tableTotal = sqlParam.getPerDbTableNum();
            String fileNamePrefix = sqlParam.getFileNamePrefix();
            boolean fileSplitByDbIndex = sqlParam.isFileSplitByDbIndex();
            String sourceFileName = sqlParam.getSourceFileName();
            StringBuilder fileName = new StringBuilder(fileNamePrefix);
            StringBuilder result = new StringBuilder();
            for (int dbIndex = sqlParam.getDbStart(); dbIndex < dbTotal; ++dbIndex) {
                int startTableIndex = 0;
                int endTableIndex = tableTotal - 1;
                if (fileSplitByDbIndex) {
                    result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sourceFileName));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(dbIndex).append("_").append(startTableIndex).append("~")
                            .append(endTableIndex).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                            StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sourceFileName));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~")
                        .append(sqlParam.getDbStart() + sqlParam.getDbTotal() - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateXXTableSqlFile(GenerateSQLParam sqlParam) {
        try {
            int dbTotal = sqlParam.getDbTotal();
            int tableNumPerDb = sqlParam.getPerDbTableNum();
            String fileNamePrefix = sqlParam.getFileNamePrefix();
            boolean fileSplitByDbIndex = sqlParam.isFileSplitByDbIndex();
            String sourceFileName = sqlParam.getSourceFileName();
            StringBuilder fileName = new StringBuilder(fileNamePrefix);
            StringBuilder result = new StringBuilder();
            for (int dbIndex = sqlParam.getDbStart(); dbIndex < dbTotal; ++dbIndex) {
                int startTableIndex = tableNumPerDb * dbIndex;
                int endTableIndex = tableNumPerDb * (1 + dbIndex) - 1;
                if (fileSplitByDbIndex) {
                    result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sourceFileName));
                    fileName = new StringBuilder(fileNamePrefix);
                    fileName.append("_").append(dbIndex).append("_").append(startTableIndex).append("~")
                            .append(endTableIndex).append(".sql");
                    FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                            StandardCharsets.UTF_8);
                    result = new StringBuilder();
                } else {
                    result.append(replaceTableSql(dbIndex, startTableIndex, endTableIndex, sourceFileName));
                }
            }
            if (!fileSplitByDbIndex) {
                fileName.append("_").append(sqlParam.getDbStart()).append("~")
                        .append(sqlParam.getDbStart() + sqlParam.getDbTotal() - 1).append(".sql");
                FileUtils.writeStringToFile(new File(sqlParam.getTargetDirectory() + fileName), result.toString(),
                        StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String replaceTableSqlForMonth(LocalDate startLocaDate, LocalDate endLocaDate, String sourceFileName, String formate) {
        String dbIndexKey = "dbidx";
        String tableIndexKey = "tableidx";
        String sql = SqlUtils.getResourceAsString(GenerateSqlUtil.class, sourceFileName);
        StringBuilder sb = new StringBuilder();
        LocalDate currentDay = startLocaDate;
        while (!currentDay.isAfter(endLocaDate)) {
            sb.append(sql.replaceAll(dbIndexKey, String.valueOf(currentDay.getYear()))
                    .replaceAll(tableIndexKey, LocalDateTimeUtil.format(currentDay, formate))
                    .replaceAll("\"", "")).append("\n");
            currentDay = currentDay.plusMonths(1);
        }
        return sb.toString();
    }

    private static String replaceTableSqlForDay(LocalDate startLocaDate, LocalDate endLocaDate, String sourceFileName, String formate) {
        String dbIndexKey = "dbidx";
        String tableIndexKey = "tableidx";
        String sql = SqlUtils.getResourceAsString(GenerateSqlUtil.class, sourceFileName);
        StringBuilder sb = new StringBuilder();
        LocalDate currentDay = startLocaDate;
        while (!currentDay.isAfter(endLocaDate)) {
            sb.append(sql.replaceAll(dbIndexKey, String.valueOf(currentDay.getYear()))
                    .replaceAll(tableIndexKey, LocalDateTimeUtil.format(currentDay, formate))
                    .replaceAll("\"", "")).append("\n");
            currentDay = currentDay.plusDays(1);
        }
        return sb.toString();
    }

    private static String replaceTableSql(int dbIndex, int tableStartIndex, int tableEndIndex, String sourceFileName) {
        String dbIndexKey = "dbidx";
        String tableIndexKey = "tableidx";
        String sql = SqlUtils.getResourceAsString(GenerateSqlUtil.class, sourceFileName);
        StringBuilder sb = new StringBuilder();
        for (int j = tableStartIndex; j <= tableEndIndex; j++) {
            String dbIndexStr = String.valueOf(dbIndex);
            if (dbIndex < 10) {
                dbIndexStr = "0" + dbIndexStr;
            }
            sb.append(sql.replaceAll(dbIndexKey, dbIndexStr).replaceAll(tableIndexKey, String.valueOf(j))
                    .replaceAll("\"", "")).append("\n");
        }
        return sb.toString();
    }

    private static String getFileName(String fileName, String split) {
        return fileName.substring(0, fileName.indexOf("db_paycore_order") + 16) + split + fileName.substring(
                fileName.indexOf("db_paycore_order") + 16, fileName.length());
    }

    @Data
    @Accessors(chain = true)
    public static class GenerateSQLParam {
        private int dbStart;
        private int dbTotal;
        private int perDbTableNum;
        private String fileNamePrefix;
        private boolean fileSplitByDbIndex;
        private String sourceFileName;
        private String targetDirectory;
        private String dateFormat;
    }

}
