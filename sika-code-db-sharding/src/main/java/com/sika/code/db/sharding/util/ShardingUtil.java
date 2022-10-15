package com.sika.code.db.sharding.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分片工具类
 * </p >
 *
 * @author sikadai
 * @since 2022/9/16 18:26
 */
public class ShardingUtil {

    public static String buildShardingDbTableName(String dbName, String shardDbName, String tableName, String shardTableName) {
        // 分片库名不为空-分片表名为空-则只分库不分表
        if (CharSequenceUtil.isNotBlank(shardDbName) && CharSequenceUtil.isBlank(shardTableName)) {
            shardTableName = tableName;
        }
        // 分片库名不为空-分片表名为空-则只分表不分库
        if (CharSequenceUtil.isBlank(shardDbName) && CharSequenceUtil.isNotBlank(shardTableName)) {
            shardDbName = dbName;
        }
        return CharSequenceUtil.join(StrPool.DOT, shardDbName, shardTableName);
    }

    /**
     * <p>
     * 获取xxYY的db值
     * </p >
     *
     * @param shardingDbValue
     * @return java.lang.String
     * @author sikadai
     * @since 2022/9/16 18:15
     */
    public static int getXxYyValue(Object shardingDbValue, Integer dbOrTableNum, boolean table) {
        String splitParamValueStr = shardingDbValue.toString();
        int hash = Math.abs(splitParamValueStr.hashCode());
        int dbTableValue;
        if (table) {
            dbTableValue = hash / dbOrTableNum % dbOrTableNum;
        } else {
            dbTableValue = hash % dbOrTableNum;
        }
        return dbTableValue;
    }

    /**
     * <p>
     * 格式化日期
     * </p >
     *
     * @param shardingDbValue
     * @param format
     * @return java.lang.String
     * @author sikadai
     * @since 2022/9/16 18:15
     */
    public static String formatDate(Object shardingDbValue, String format) {
        if (shardingDbValue instanceof Date) {
            return DateUtil.format((Date) shardingDbValue, format);
        } else if (shardingDbValue instanceof LocalDate) {
            return LocalDateTimeUtil.format((LocalDate) shardingDbValue, format);
        } else if (shardingDbValue instanceof LocalDateTime) {
            return LocalDateTimeUtil.format((LocalDateTime) shardingDbValue, format);
        } else {
            throw new RuntimeException(StrUtil.format("不支持的日期类型【{}】", shardingDbValue.getClass()));
        }
    }

    /**
     * <p>
     * 计算当前日期是一年的第几周
     * </p >
     *
     * @param shardingDbValue
     * @return java.lang.String
     * @author sikadai
     * @since 2022/9/16 18:15
     */
    public static int servenDayOfYear(Object shardingDbValue) {
        Date waitComputeWeek = null;
        if (shardingDbValue instanceof Date) {
            waitComputeWeek = (Date) shardingDbValue;
            return DateUtil.weekOfYear((Date) shardingDbValue);
        } else if (shardingDbValue instanceof LocalDate) {
            waitComputeWeek = Date.from(((LocalDate) shardingDbValue).atStartOfDay().atZone(ZoneOffset.systemDefault()).toInstant());
        } else if (shardingDbValue instanceof LocalDateTime) {
            waitComputeWeek = Date.from(((LocalDateTime) shardingDbValue).atZone(ZoneOffset.systemDefault()).toInstant());
        } else {
            throw new RuntimeException(StrUtil.format("不支持的日期类型【{}】", shardingDbValue.getClass()));
        }
        return getSevenDayNumForYear(waitComputeWeek);
    }

    /**
     * 生成32 * 32 分库分表规则的语句
     *
     * @param dbCount                    : 数据库的数量
     * @param tableCount                 : 表的数量
     * @param createPrefix               : 需要替换的前缀
     * @param sourceDbFile               : 源数据库文件
     * @param sourceTableFile:           源表文件
     * @param targetDbFile               ：输出的目标数据库文件
     * @param targetTableFile：输出的没目标表的文件
     */
    public static void generateXXYYDbFile(int dbCount, int tableCount, String createPrefix,
                                          File sourceDbFile, File sourceTableFile,
                                          File targetDbFile, File targetTableFile) {
        doGenerateXxDb(dbCount, sourceDbFile, targetDbFile);
        doGenerateYyTable(dbCount, tableCount, sourceTableFile, targetTableFile, createPrefix);
    }


    /**
     * 生成年库月表
     *
     * @param beginYear                  ： 起始年
     * @param endYear                    : 结束年
     * @param createPrefix               : 需要替换的前缀
     * @param sourceDbFile               : 源数据库文件
     * @param sourceTableFile:           源表文件
     * @param targetDbFile               ：输出的目标数据库文件
     * @param targetTableFile：输出的没目标表的文件
     */
    public static void generateYearMonthDbFile(int beginYear, int endYear, String createPrefix,
                                               File sourceDbFile, File sourceTableFile,
                                               File targetDbFile, File targetTableFile) {

        doGenerateYearDb(beginYear, endYear, sourceDbFile, targetDbFile);
        doGenerateMonthTable(beginYear, endYear, sourceTableFile, targetTableFile, createPrefix);
    }

    /**
     * 生成年库七天表
     *
     * @param beginYear                  ： 起始年
     * @param endYear                    : 结束年
     * @param createPrefix               : 需要替换的前缀
     * @param sourceDbFile               : 源数据库文件
     * @param sourceTableFile:           源表文件
     * @param targetDbFile               ：输出的目标数据库文件
     * @param targetTableFile：输出的没目标表的文件
     */
    public static void generateYearSevenDayDbFile(int beginYear, int endYear, String createPrefix,
                                                  File sourceDbFile, File sourceTableFile,
                                                  File targetDbFile, File targetTableFile) {
        doGenerateYearDb(beginYear, endYear, sourceDbFile, targetDbFile);
        doGenerateWeekTable(beginYear, endYear, sourceTableFile, targetTableFile, createPrefix);
    }

    /**
     * 生成年库天表
     *
     * @param beginYear                  ： 起始年
     * @param endYear                    : 结束年
     * @param createPrefix               : 需要替换的前缀
     * @param sourceDbFile               : 源数据库文件
     * @param sourceTableFile:           源表文件
     * @param targetDbFile               ：输出的目标数据库文件
     * @param targetTableFile：输出的没目标表的文件
     */
    public static void generateYearDayDbFile(int beginYear, int endYear, String createPrefix,
                                             File sourceDbFile, File sourceTableFile,
                                             File targetDbFile, File targetTableFile) {
        doGenerateYearDb(beginYear, endYear, sourceDbFile, targetDbFile);
        doGenerateDayTable(beginYear, endYear, sourceTableFile, targetTableFile, createPrefix);
    }

    protected static void doGenerateXxDb(int dbNum, File sourceDbFile, File targetDbFile) {
        FileUtil.del(targetDbFile);
        for (int i = 0; i < dbNum; ++i) {
            String dbStr = String.valueOf(i);
            List<String> strings = FileUtil.readLines(sourceDbFile, Charset.defaultCharset());
            List<String> targetStrs = Lists.newArrayList();
            for (String dbCreate : strings) {
                targetStrs.add(StrUtil.format(dbCreate, dbStr));
            }
            FileUtil.appendLines(targetStrs, targetDbFile, CharsetUtil.UTF_8);
        }
    }

    protected static void doGenerateYyTable(int dbNum, int tableNum,
                                            File sourceTableFile, File targetTableFile, String createPrefix) {
        FileUtil.del(targetTableFile);
        for (int i = 0; i < dbNum; ++i) {
            for (int j = 0; j < tableNum; ++j) {
                String dbStr = String.valueOf(i);
                String tableStr = String.valueOf(j);
                List<String> strings = FileUtil.readLines(sourceTableFile, Charset.defaultCharset());
                List<String> targetStrs = Lists.newArrayList();
                for (String tableStrings : strings) {
                    if (StrUtil.startWithIgnoreCase(tableStrings, createPrefix)) {
                        targetStrs.add(StrUtil.format(tableStrings, dbStr, tableStr));
                    } else {
                        targetStrs.add(tableStrings);
                    }
                }
                FileUtil.appendLines(targetStrs, targetTableFile, CharsetUtil.UTF_8);
            }
        }
    }

    protected static void doGenerateMonthTable(int beginYear, int endYear,
                                               File sourceTableFile, File targetTableFile, String createPrefix) {
        FileUtil.del(targetTableFile);
        int dbCount = endYear - beginYear + 1;
        int dbYear = beginYear;
        for (int i = 0; i < dbCount; ++i) {
            dbYear = beginYear + i;
            for (int j = 1; j <= 12; ++j) {
                String month = StrUtil.fillBefore(String.valueOf(j), '0', 2);
                String yearMonth = dbYear + month;
                Date beginMonthDay = DateUtil.beginOfMonth(DateUtil.parse(yearMonth, "yyyyMM"));
                List<String> strings = FileUtil.readLines(sourceTableFile, Charset.defaultCharset());
                List<String> targetStrs = Lists.newArrayList();
                String formatDate = DateUtil.format(beginMonthDay, "yyyyMM");
                for (String dbCreate : strings) {
                    if (StrUtil.startWithIgnoreCase(dbCreate, createPrefix)) {
                        targetStrs.add(StrUtil.format(dbCreate, dbYear, formatDate));
                    } else {
                        targetStrs.add(dbCreate);
                    }
                }
                FileUtil.appendLines(targetStrs, targetTableFile, CharsetUtil.UTF_8);
            }
        }
    }

    protected static void doGenerateWeekTable(int beginYear, int endYear,
                                              File sourceTableFile, File targetTableFile, String createPrefix) {
        FileUtil.del(targetTableFile);
        int dbCount = endYear - beginYear + 1;
        for (int i = 0; i < dbCount; ++i) {
            int dbYear = beginYear + i;
            Date endDayOfYear = DateUtil.endOfYear(DateUtil.parse(String.valueOf(dbYear), "yyyy"));
            int weekNum = getSevenDayNumForYear(endDayOfYear);
            for (int j = 1; j <= weekNum; ++j) {
                String tableStr = String.valueOf(j);
                List<String> strings = FileUtil.readLines(sourceTableFile, Charset.defaultCharset());
                List<String> targetStrs = Lists.newArrayList();
                for (String tableStrings : strings) {
                    if (StrUtil.startWithIgnoreCase(tableStrings, createPrefix)) {
                        targetStrs.add(StrUtil.format(tableStrings, String.valueOf(dbYear), tableStr));
                    } else {
                        targetStrs.add(tableStrings);
                    }
                }
                FileUtil.appendLines(targetStrs, targetTableFile, CharsetUtil.UTF_8);
            }
        }
    }

    /**
     * 获取当前日期在当年的第几个七天
     *
     * @param currentDay
     * @return
     */
    public static int getSevenDayNumForYear(Date currentDay) {
        Date startOfYear = DateUtil.beginOfYear(currentDay);
        long dayNum = DateUtil.betweenDay(startOfYear, currentDay, true) + 1;
        int weekNum = (int) (dayNum / 7);
        int weekNumYu = (int) (dayNum % 7);
        if (weekNumYu > 0) {
            weekNum += 1;
        }
        return weekNum;
    }

    protected static void doGenerateDayTable(int beginYear, int endYear,
                                             File sourceTableFile, File targetTableFile, String createPrefix) {

        FileUtil.del(targetTableFile);
        int dbCount = endYear - beginYear + 1;
        int dbYear = beginYear;
        for (int i = 0; i < dbCount; ++i) {
            dbYear = beginYear + i;
            for (int j = 1; j <= 12; ++j) {
                String month = StrUtil.fillBefore(String.valueOf(j), '0', 2);
                String yearMonth = dbYear + month;
                Date beginMonthDay = DateUtil.beginOfMonth(DateUtil.parse(yearMonth, "yyyyMM"));
                int lengthOfMonth = DateUtil.lengthOfMonth(j, DateUtil.isLeapYear(dbYear));
                for (int k = 0; k < lengthOfMonth; ++k) {
                    String formatDate = DateUtil.format(DateUtil.offsetDay(beginMonthDay, k), "yyyyMMdd");
                    List<String> strings = FileUtil.readLines(sourceTableFile, Charset.defaultCharset());
                    List<String> targetStrs = Lists.newArrayList();
                    for (String dbCreate : strings) {
                        if (StrUtil.startWithIgnoreCase(dbCreate, createPrefix)) {
                            targetStrs.add(StrUtil.format(dbCreate, dbYear, formatDate));
                        } else {
                            targetStrs.add(dbCreate);
                        }
                    }
                    FileUtil.appendLines(targetStrs, targetTableFile, CharsetUtil.UTF_8);
                }
            }
        }
    }

    protected static void doGenerateYearDb(int beginYear, int endYear,
                                           File sourceDbFile, File targetDbFile) {
        FileUtil.del(targetDbFile);
        int dbCount = endYear - beginYear + 1;
        int dbYear = beginYear;
        for (int i = 0; i < dbCount; ++i) {
            List<String> strings = FileUtil.readLines(sourceDbFile, Charset.defaultCharset());
            List<String> targetStrs = Lists.newArrayList();
            for (String dbCreate : strings) {
                targetStrs.add(StrUtil.format(dbCreate, dbYear++));
            }
            FileUtil.appendLines(targetStrs, targetDbFile, CharsetUtil.UTF_8);
        }
    }

}
