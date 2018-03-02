package com.dq.easy.cloud.model.common.date.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 日期格式化工具类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月3日 下午5:09:17
 */
public class DqDateFormatUtils {
	/** 时间格式---精确到天---yyyyMMdd */
	public static final String FORMAT_SHORT = "yyyyMMdd";
	/** 时间格式---精确到秒---yyyyMMddHHmmss */
	public static final String FORMAT_LONG = "yyyyMMddHHmmss";
	/** 时间格式---精确到毫秒---yyyyMMddHHmmssSSS */
	public static final String FORMAT_LONG_MILLIS = "yyyyMMddHHmmssSSS";
	/** 时间格式---精确到秒---yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式---精确到天---yyyy-MM-dd */
	public static final String FORMAT_NORMAL_DAY = "yyyy-MM-dd";
	/** 时间格式---精确到月---yyyy-MM */
	public static final String FORMAT_NORMAL_MONTH = "yyyy-MM";
	/** 时区---东八时区---GMT+8 */
	public static final String EAST_EIGHT_TIME_ZONE = "GMT+8";
	

	/**
     * <p>Formats a date/time into a specific pattern using the UTC time zone.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @return the formatted date
     */
    public static String formatUTC(long millis, String pattern) {
        return DateFormatUtils. formatUTC(millis, pattern);
    }

    /**
     * <p>Formats a date/time into a specific pattern using the UTC time zone.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @return the formatted date
     */
    public static String formatUTC(Date date, String pattern) {
        return DateFormatUtils.formatUTC(date, pattern);
    }
    
    /**
     * <p>Formats a date/time into a specific pattern using the UTC time zone.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String formatUTC(long millis, String pattern, Locale locale) {
    	return DateFormatUtils.formatUTC(millis, pattern, locale);
    }

    /**
     * <p>Formats a date/time into a specific pattern using the UTC time zone.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String formatUTC(Date date, String pattern, Locale locale) {
    	return DateFormatUtils.formatUTC(date, pattern, locale);
    }
    
    /**
     * <p>Formats a date/time into a specific pattern.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @return the formatted date
     */
    public static String format(long millis, String pattern) {
    	return DateFormatUtils.format(millis, pattern);
    }

    /**
     * <p>Formats a date/time into a specific pattern.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @return the formatted date
     */
    public static String format(Date date, String pattern) {
    	if(DqBaseUtils.isNull(date)){
    		return null;
    	}
    	if(DqStringUtils.isEmpty(pattern)){
    		pattern = FORMAT_NORMAL;
		}
    	return DateFormatUtils.format(date, pattern);
    }

    /**
     * <p>Formats a calendar into a specific pattern.</p>
     * 
     * @param calendar  the calendar to format, not null
     * @param pattern  the pattern to use to format the calendar, not null
     * @return the formatted calendar
     * @see FastDateFormat#format(Calendar)
     */
    public static String format(Calendar calendar, String pattern) {
    	return DateFormatUtils.format(calendar, pattern);
    }
    
    /**
     * <p>Formats a date/time into a specific pattern in a time zone.</p>
     * 
     * @param millis  the time expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(long millis, String pattern, TimeZone timeZone) {
    	return DateFormatUtils.format(millis, pattern, timeZone);
    }

    /**
     * <p>Formats a date/time into a specific pattern in a time zone.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
    	return DateFormatUtils.format(date, pattern, timeZone);
    }

    /**
     * <p>Formats a calendar into a specific pattern in a time zone.</p>
     * 
     * @param calendar  the calendar to format, not null
     * @param pattern  the pattern to use to format the calendar, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @return the formatted calendar
     * @see FastDateFormat#format(Calendar)
     */
    public static String format(Calendar calendar, String pattern, TimeZone timeZone) {
    	return DateFormatUtils.format(calendar, pattern, timeZone);
    }

    /**
     * <p>Formats a date/time into a specific pattern in a locale.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(long millis, String pattern, Locale locale) {
    	return DateFormatUtils.format(millis, pattern, locale);
    }

    /**
     * <p>Formats a date/time into a specific pattern in a locale.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(Date date, String pattern, Locale locale) {
    	return DateFormatUtils.format(date, pattern, locale);
    }

    /**
     * <p>Formats a calendar into a specific pattern in a locale.</p>
     * 
     * @param calendar  the calendar to format, not null
     * @param pattern  the pattern to use to format the calendar, not null
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted calendar
     * @see FastDateFormat#format(Calendar)
     */
    public static String format(Calendar calendar, String pattern, Locale locale) {
    	return DateFormatUtils.format(calendar, pattern, locale);
    }

    /**
     * <p>Formats a date/time into a specific pattern in a time zone  and locale.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(long millis, String pattern, TimeZone timeZone, Locale locale) {
    	return DateFormatUtils.format(millis, pattern, timeZone, locale);
    }

    /**
     * <p>Formats a date/time into a specific pattern in a time zone  and locale.</p>
     * 
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
    	return DateFormatUtils.format(date, pattern, timeZone, locale);
    }

    /**
     * <p>Formats a calendar into a specific pattern in a time zone  and locale.</p>
     * 
     * @param calendar  the calendar to format, not null
     * @param pattern  the pattern to use to format the calendar, not null
     * @param timeZone  the time zone  to use, may be <code>null</code>
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted calendar
     * @see FastDateFormat#format(Calendar)
     */
    public static String format(Calendar calendar, String pattern, TimeZone timeZone, Locale locale) {
    	return DateFormatUtils.format(calendar, pattern, timeZone, locale);
    }
}
