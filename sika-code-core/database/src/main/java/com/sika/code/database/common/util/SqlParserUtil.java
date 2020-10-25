package com.sika.code.database.common.util;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL语句解析器类.可以整体解析一个sql的区段
 * 
 */
public final class SqlParserUtil {

	private static final String VELOCITY_VARIABLE_PREFIX = "'\\${";

	private static final String VELOCITY_VARIABLE_SUFFIX = "}'";

	/**
	 * 解析选择的列
	 * 
	 */
	public static String parseCols(String sql) {
		String regex = "(select)(.+)(from)";
		return getMatchedString(regex, sql);
	}

	/**
	 * 解析选择的表
	 * 
	 */
	public static String parseTables(String sql) {
		String regex = "";
		if (isContains(sql, "\\s+where\\s+")) {
			regex = "(from)(.+)(where)";
		} else {
			regex = "(from)(.+)($)";
		}
		return getMatchedString(regex, sql);
	}

	/**
	 * 从sql中获取全部@符号的变量
	 * 
	 * @param s sql
	 * @return 变量的集合
	 */
	public static List<String> parseAtSymbol(String s) {
		List<String> results = new ArrayList<String>();
		Pattern p = Pattern.compile("@([\\w]*) ");
		Matcher m = p.matcher(s);
		while (!m.hitEnd() && m.find()) {
			results.add(m.group(1));
		}
		return results;
	}

	/**
	 * 替换@符号为velocity的#{param}
	 * 
	 * @param s sql
	 * @return String 变量的集合
	 */
	public static String replaceAtSymbol(String s, List<String> params) {
		for (String param : params) {
			String paramTemp = VELOCITY_VARIABLE_PREFIX + param + VELOCITY_VARIABLE_SUFFIX;
			s = s.replaceFirst("@([\\w]*)", paramTemp);
		}
		return s;
	}

	/**
	 * 解析查找条件
	 * 
	 */
	public static String parseConditions(String sql) {
		String regex = "";
		if (isContains(sql, "\\s+where\\s+")) {
			// 包括Where，有条件
			if (isContains(sql, "group\\s+by")) {
				// 条件在where和group by之间
				regex = "(where)(.+)(group\\s+by)";
			} else if (isContains(sql, "order\\s+by")) {
				// 条件在where和order by之间
				regex = "(where)(.+)(order\\s+by)";
			} else {
				// 条件在where到字符串末尾
				regex = "(where)(.+)($)";
			}
		} else {
			// 不包括where则条件无从谈起，返回即可
			return "";
		}
		return getMatchedString(regex, sql);
	}

	/**
	 * 解析GroupBy的字段
	 * 
	 */
	public static String parseGroupCols(String sql) {
		String regex = "";
		if (isContains(sql, "group\\s+by")) {
			// 包括GroupBy，有分组字段
			if (isContains(sql, "order\\s+by")) {
				// group by 后有order by
				regex = "(group\\s+by)(.+)(order\\s+by)";
			} else {
				// group by 后无order by
				regex = "(group\\s+by)(.+)($)";
			}
		} else {
			// 不包括GroupBy则分组字段无从谈起，返回即可
			return "";
		}
		return getMatchedString(regex, sql);
	}

	/**
	 * 解析OrderBy的字段
	 * 
	 */
	public static String parseOrderCols(String sql) {
		String regex = "";
		if (isContains(sql, "order\\s+by")) {
			// 包括GroupBy，有分组字段
			regex = "(order\\s+by)(.+)($)";
		} else {
			// 不包括GroupBy则分组字段无从谈起，返回即可
			return "";
		}
		return getMatchedString(regex, sql);
	}

	/**
	 * 从文本text中找到regex首次匹配的字符串，不区分大小写
	 * 
	 * @param regex： 正则表达式
	 * @param text：欲查找的字符串
	 * @return String regex首次匹配的字符串，如未匹配返回空
	 */
	private static String getMatchedString(String regex, String text) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			return matcher.group(2);
		}
		return null;
	}

	/**
	 * 看word是否在lineText中存在，支持正则表达式
	 * 
	 * @param lineText
	 * @param word
	 * @return boolean
	 */
	private static boolean isContains(String lineText, String word) {
		Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(lineText);
		return matcher.find();
	}

	/**
	 * 处理拦截的sql，主要包括格式化去掉空格，将传入的参数填值进去
	 * 
	 * @param interceptSql
	 * @param mappedStatement
	 * @param boundSql
	 * @return String
	 */
	public static String handleSql(String interceptSql, MappedStatement mappedStatement, BoundSql boundSql) {
		String sql = interceptSql.replaceAll("[\\s]+", " ").trim();
		Configuration configuration = mappedStatement.getConfiguration();
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

		try {
			if (parameterObject != null && parameterMappings != null && !parameterMappings.isEmpty()) {
				TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

				if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
					sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
				} else {
					MetaObject metaObject = configuration.newMetaObject(parameterObject);

					for (ParameterMapping parameterMapping : parameterMappings) {
						String propertyName = parameterMapping.getProperty();
						if (metaObject.hasGetter(propertyName)) {
							Object obj = metaObject.getValue(propertyName);
							sql = sql.replaceFirst("\\?", getParameterValue(obj));
						} else if (boundSql.hasAdditionalParameter(propertyName)) {
							Object obj = boundSql.getAdditionalParameter(propertyName);
							sql = sql.replaceFirst("\\?", getParameterValue(obj));
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Parse sql exception, " + sql, e);
		}

		return sql;
	}
	
	/**
	 * 获取参数值
	 * 
	 * @param obj
	 * @return String
	 */
	public static String getParameterValue(Object obj) {
		String value = null;
		
		if (obj == null) {
			value = "null";
		} else if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			Date date = (Date) obj;
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(date) + "'";
		} else {
			value = obj.toString();
		}
		
		return getSafeString(value);
	}

	public static String getSafeString(String str) {
		return str.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
	}
	
	/**
	 * 测试 demo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// List<String> ls = new ArrayList<String>();
		// ls.add("select * from dual");
		// ls.add("SELECT * frOm dual");
		// ls.add("Select C1,c2 From tb");
		// ls.add("select c1,c2 from tb");
		// ls.add("select count(*) from t1");
		// ls.add("select c1,c2,c3 from t1 where condi1=1 ");
		// ls.add("Select c1,c2,c3 From t1 Where condi1=1 ");
		// ls.add("select c1,c2,c3 from t1,t2 where condi3=3 or condi4=5 order by o1,o2");
		// ls.add("Select c1,c2,c3 from t1,t2 Where condi3=3 or condi4=5 Order by o1,o2");
		// ls.add("select c1,c2,c3 from t1,t2,t3 where condi1=5 and condi6=6 or condi7=7 group by g1,g2");
		// ls.add("Select c1,c2,c3 From t1,t2,t3 Where condi1=5 and condi6=6 or condi7=7 Group by g1,g2");
		// ls.add("Select c1,c2,c3 From t1,t2,t3 Where condi1=5 and condi6=6 or condi7=7 Group by g1,g2,g3 order by g2,g3");
		// for (String sql : ls) {
		// System.out.println(SqlParser.parseOrderCols(sql));
		// // System.out.println(sql);
		// }
		parseConditions("");
		//List<String> params = parseAtSymbol("SELECT DISTINCT outbound_order.order_no FROM outbound_order INNER JOIN outbound_order_detail ON outbound_order.order_no = outbound_order_detail.order_no WHERE 1 = 1 AND (outbound_order.order_type = 'ORDER_TYPE_SO'  OR outbound_order.order_type = 'ORDER_TYPE_RO'  OR outbound_order.order_type = 'PR'  OR outbound_order.order_type = 'RTSG'  OR outbound_order.order_type = 'STO'  OR outbound_order.order_type = 'SAO'  OR outbound_order.order_type = 'JCK'  OR outbound_order.order_type = 'BO' ) AND (outbound_order_detail.Inventory_status = 'LOCATION_STATUS_QUALIFIED'  OR outbound_order_detail.Inventory_status IS NULL)  and outbound_order.order_no = @ORDER_NO and outbound_order_detail.order_line_no = @ORDER_LINE_NO ");
		System.out.println(SqlParserUtil.parseConditions("SELECT DISTINCT outbound_order.order_no FROM outbound_order INNER JOIN outbound_order_detail ON outbound_order.order_no = outbound_order_detail.order_no WHERE 1 = 1 AND (outbound_order.order_type = 'ORDER_TYPE_SO'  OR outbound_order.order_type = 'ORDER_TYPE_RO'  OR outbound_order.order_type = 'PR'  OR outbound_order.order_type = 'RTSG'  OR outbound_order.order_type = 'STO'  OR outbound_order.order_type = 'SAO'  OR outbound_order.order_type = 'JCK'  OR outbound_order.order_type = 'BO' ) AND (outbound_order_detail.Inventory_status = 'LOCATION_STATUS_QUALIFIED'  OR outbound_order_detail.Inventory_status IS NULL)  and outbound_order.order_no = @ORDER_NO and outbound_order_detail.order_line_no = @ORDER_LINE_NO "));
	}
}