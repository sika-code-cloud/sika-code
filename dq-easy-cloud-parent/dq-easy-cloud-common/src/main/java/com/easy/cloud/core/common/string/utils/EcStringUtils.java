package com.dq.easy.cloud.module.common.string.utils;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;

/**
 * 以org.apache.commons包为基础封装的String操作类
 * @ClassName : DqStringUtils 
 * @Description : string工具类 
 * @author daiqi
 * @date 2017年12月5日 下午2:04:45 
 *
 */
public class DqStringUtils {
	
	/**
     * The empty String <code>""</code>.
     */
    public static final String EMPTY = DqSymbol.EMPTY;
    /** 分割符---英文冒号---:*/
    public static final String SPLIT_COLON = DqSymbol.SPLIT_COLON;
    /** 初始化相关String容器的容量--512 */
    private static final int INIT_CAPACITY = 512;
	
	/**
	 * 将首字母大写
     * <p>Capitalizes a String changing the first letter to title case as
     * per {@link Character#toTitleCase(char)}. No other letters are changed.</p>
     *
     * <p>For a word based algorithm, see {@link WordUtils#capitalize(String)}.
     * A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str  the String to capitalize, may be null
     * @return the capitalized String, <code>null</code> if null String input
	 * @author daiqi
	 * @date 2017年12月5日 下午8:21:54
	 */
	public static String capitalize(String str){
		return StringUtils.capitalize(str);
	}
	
	/**
	 * 补全字符串的长度并将该字符串放到中间
     * <p>Centers a String in a larger String of size <code>size</code>
     * using the space character (' ').<p>
     *
     * <p>If the size is less than the String length, the String is returned.
     * A <code>null</code> String returns <code>null</code>.
     * A negative size is treated as zero.</p>
     *
     * <p>Equivalent to <code>center(str, size, " ")</code>.</p>
     *
     * <pre>
     * StringUtils.center(null, *)   = null
     * StringUtils.center("", 4)     = "    "
     * StringUtils.center("ab", -1)  = "ab"
     * StringUtils.center("ab", 4)   = " ab "
     * StringUtils.center("abcd", 2) = "abcd"
     * StringUtils.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str  the String to center, may be null
     * @param size  the int size of new String, negative treated as zero
     * @return centered String, <code>null</code> if null String input
     * @author daiqi
	 * @date 2017年12月5日 下午8:21:54
     */
	public static String center(String str, int size){
		return StringUtils.center(str, size);
	}
	
	/**
	 * 判断str是否包含searchStr，若包含返回true，否则返回false
     * <p>Checks if String contains a search String, handling <code>null</code>.
     * This method uses {@link String#indexOf(String)}.</p>
     *
     * <p>A <code>null</code> String will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return true if the String contains the search String,
     * false if not or <code>null</code> string input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean contains(String str,String searchStr){
		return StringUtils.contains(str, searchStr);
	}
	
	/**
	 * 判断str是否包含searchStr，若包含返回true，否则返回false(忽略大小写)
     * <p>Checks if String contains a search String irrespective of case,
     * handling <code>null</code>. Case-insensitivity is defined as by
     * {@link String#equalsIgnoreCase(String)}.
     *
     * <p>A <code>null</code> String will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.contains(null, *) = false
     * StringUtils.contains(*, null) = false
     * StringUtils.contains("", "") = true
     * StringUtils.contains("abc", "") = true
     * StringUtils.contains("abc", "a") = true
     * StringUtils.contains("abc", "z") = false
     * StringUtils.contains("abc", "A") = true
     * StringUtils.contains("abc", "Z") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return true if the String contains the search String irrespective of
     * case or false if not or <code>null</code> string input
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean containsIgnoreCase(String str, String searchStr){
		return StringUtils.containsIgnoreCase(str, searchStr);
	}
	
	/**
	 * 删除字符串所有的空白字符 保罗/n /t ""等
     * <p>Deletes all whitespaces from a String as defined by
     * {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace("")           = ""
     * StringUtils.deleteWhitespace("abc")        = "abc"
     * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  the String to delete whitespace from, may be null
     * @return the String without whitespaces, <code>null</code> if null String input
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String deleteWhitespace(String str){
		return StringUtils.deleteWhitespace(str);
	}
	
	/**
	 * 判断str是否以suffix结束
     * <p>Check if a String ends with a specified suffix.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.endsWith(null, null)      = true
     * StringUtils.endsWith(null, "def")     = false
     * StringUtils.endsWith("abcdef", null)  = false
     * StringUtils.endsWith("abcdef", "def") = true
     * StringUtils.endsWith("ABCDEF", "def") = false
     * StringUtils.endsWith("ABCDEF", "cde") = false
     * </pre>
     *
     * @see java.lang.String#endsWith(String)
     * @param str  the String to check, may be null
     * @param suffix the suffix to find, may be null
     * @return <code>true</code> if the String ends with the suffix, case sensitive, or
     *  both <code>null</code>
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean endsWith(String str, String suffix){
		return StringUtils.endsWith(str, suffix);
	}
	
	/**
	 * 判断str是否以suffix结束(忽略大小写)
     * <p>Case insensitive check if a String ends with a specified suffix.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.endsWithIgnoreCase(null, null)      = true
     * StringUtils.endsWithIgnoreCase(null, "def")     = false
     * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
     * </pre>
     *
     * @see java.lang.String#endsWith(String)
     * @param str  the String to check, may be null
     * @param suffix the suffix to find, may be null
     * @return <code>true</code> if the String ends with the suffix, case insensitive, or
     *  both <code>null</code>
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean endsWithIgnoreCase(String str, String suffix){
		return StringUtils.endsWithIgnoreCase(str, suffix);
	}
	
	/**
	 * 判断两个字符串是否相等
     * <p>Compares two Strings, returning <code>true</code> if they are equal.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.equals(null, null)   = false
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     *
     * @see java.lang.String#equals(Object)
     * @param str1  the first String, may be null
     * @param str2  the second String, may be null
     * @return <code>true</code> if the Strings are equal, case sensitive, or
     *  both <code>null</code>
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean equals(String str1, String str2){
		if (str1 == null || str2 == null) {
			return false;
		}
		return StringUtils.equals(str1, str2);
	}
	
	/**
	 * 判断两个字符串是否不相等
     * <p>Compares two Strings, returning <code>true</code> if they are not equal.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = flase
     * StringUtils.equals("abc", "ABC") = true
     * </pre>
     *
     * @see java.lang.String#equals(Object)
     * @param str1  the first String, may be null
     * @param str2  the second String, may be null
     * @return <code>true</code> if the Strings are not equal, case sensitive, or
     *  both <code>null</code>
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean notEquals(String str1, String str2){
		return !StringUtils.equals(str1, str2);
	}
	
	/**
	 * 判断两个字符串是否相等(忽略大小写)
     * <p>Compares two Strings, returning <code>true</code> if they are equal ignoring
     * the case.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered equal. Comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @see java.lang.String#equalsIgnoreCase(String)
     * @param str1  the first String, may be null
     * @param str2  the second String, may be null
     * @return <code>true</code> if the Strings are equal, case insensitive, or
     *  both <code>null</code>
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean equalsIgnoreCase(String str1, String str2){
		return StringUtils.equalsIgnoreCase(str1, str2);
	}
	
	/**
	 * 获取searchStr所在str中的下标位置
     * <p>Finds the first index within a String, handling <code>null</code>.
     * This method uses {@link String#indexOf(String)}.</p>
     *
     * <p>A <code>null</code> String will return <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf("", "")           = 0
     * StringUtils.indexOf("", *)            = -1 (except when * = "")
     * StringUtils.indexOf("aabaabaa", "a")  = 0
     * StringUtils.indexOf("aabaabaa", "b")  = 2
     * StringUtils.indexOf("aabaabaa", "ab") = 1
     * StringUtils.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return the first index of the search String,
     *  -1 if no match or <code>null</code> string input
     *  
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static int indexOf(String str, String searchStr){
		return StringUtils.indexOf(str, searchStr);
	}
	
	/**
	 * 判断str是否全部为小写字母
     * <p>Checks if the String contains only lowercase characters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.isAllLowerCase(null)   = false
     * StringUtils.isAllLowerCase("")     = false
     * StringUtils.isAllLowerCase("  ")   = false
     * StringUtils.isAllLowerCase("abc")  = true
     * StringUtils.isAllLowerCase("abC") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains lowercase characters, and is non-null
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isAllLowerCase(String str){
		return StringUtils.isAllLowerCase(str);
	}
	
	/**
	 * 判断str是否全部为大写字符
     * <p>Checks if the String contains only uppercase characters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.isAllUpperCase(null)   = false
     * StringUtils.isAllUpperCase("")     = false
     * StringUtils.isAllUpperCase("  ")   = false
     * StringUtils.isAllUpperCase("ABC")  = true
     * StringUtils.isAllUpperCase("aBC") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains uppercase characters, and is non-null
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isAllUpperCase(String str){
		return StringUtils.isAllUpperCase(str);
	}
	
	/**
	 * 判断str是否全部为小写英文字母
     * <p>Checks if the String contains only unicode letters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = true
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters, and is non-null
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isAlpha(String str){
		return StringUtils.isAlpha(str);
	}
	
	/**
	 * 判断str是否只为小写字母和数字组成
     * <p>Checks if the String contains only unicode letters or digits.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = true
     * StringUtils.isAlphanumeric("  ")   = false
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = false
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters or digits,
     *  and is non-null
     *  
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isAlphanumeric(String str){
		return StringUtils.isAlphanumeric(str);
	}
	
	/**
	 * 判断str是否为ascii码中的字符组成
     * <p>Checks if the string contains only ASCII printable characters.</p>
     * 
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     * 
     * <pre>
     * StringUtils.isAsciiPrintable(null)     = false
     * StringUtils.isAsciiPrintable("")       = true
     * StringUtils.isAsciiPrintable(" ")      = true
     * StringUtils.isAsciiPrintable("Ceki")   = true
     * StringUtils.isAsciiPrintable("ab2c")   = true
     * StringUtils.isAsciiPrintable("!ab-c~") = true
     * StringUtils.isAsciiPrintable("\u0020") = true
     * StringUtils.isAsciiPrintable("\u0021") = true
     * StringUtils.isAsciiPrintable("\u007e") = true
     * StringUtils.isAsciiPrintable("\u007f") = false
     * StringUtils.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
     * </pre>
     *
     * @param str the string to check, may be null
     * @return <code>true</code> if every character is in the range
     *  32 thru 126
     *  
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isAsciiPrintable(String str){
		return StringUtils.isAsciiPrintable(str);
	}
	/**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * DqStringUtils.isEmpty(null)      = true
     * DqStringUtils.isEmpty("")        = true
     * DqStringUtils.isEmpty(" ")       = true
     * DqStringUtils.isEmpty("bob")     = false
     * DqStringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
	 * @author daiqi
	 * @date 2017年12月5日 下午7:44:49
     */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str) || "".equals(str.trim()) || "null".equalsIgnoreCase(str);
	}
	
	/**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * DqStringUtils.isNotEmpty(null)      = false
     * DqStringUtils.isNotEmpty("")        = false
     * DqStringUtils.isNotEmpty(" ")       = true
     * DqStringUtils.isNotEmpty("bob")     = true
     * DqStringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
	 * @author daiqi
	 * @date 2017年12月5日 下午7:44:49
     */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 判断str是否全部由数字组成
     * <p>Checks if the String contains only unicode digits.
     * A decimal point is not a unicode digit and returns false.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static boolean isNumeric(String str){
		return StringUtils.isNumeric(str);
	}
	
	/**
	 * 将str转换为小写字符串
     * <p>Converts a String to lower case as per {@link String#toLowerCase()}.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     *
     * <p><strong>Note:</strong> As described in the documentation for {@link String#toLowerCase()},
     * the result of this method is affected by the current locale.
     * For platform-independent case transformations, the method {@link #lowerCase(String, Locale)}
     * should be used with a specific locale (e.g. {@link Locale#ENGLISH}).</p>
     *
     * @param str  the String to lower case, may be null
     * @return the lower cased String, <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String lowerCase(String str){
		return StringUtils.lowerCase(str);
	}
	
	/**
	 * 
	 * <p>创建默认capacity的StringBuilder对象</p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午6:06:21
	 */
	public static StringBuilder newStringBuilderDefault(){
		return newStringBuilder(INIT_CAPACITY);
	}
	
	/**
	 * 
	 * <p>
	 * 创建带有初始化容器长度的StringBuilder对象
	 * </p>
	 * <p>
	 * 若initCapacity为null或者initCapacity <= 将实用默认的INIT_CAPACITY
	 * </p>
	 * <pre></pre>
	 *
	 * @param initCapacity : Integer : StringBuilder的初始化长度
	 * @return StringBuilder
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午6:04:33
	 */
	public static StringBuilder newStringBuilder(final Integer initCapacity){
		if(DqBaseUtils.isNull(initCapacity) || initCapacity <= 0){
			return new StringBuilder(INIT_CAPACITY);
		}
		return new StringBuilder(initCapacity);
	}
	/**
	 * 从str中移除remove字符串
     * <p>Removes all occurrences of a substring from within the source string.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.
     * A <code>null</code> remove string will return the source string.
     * An empty ("") remove string will return the source string.</p>
     *
     * <pre>
     * StringUtils.remove(null, *)        = null
     * StringUtils.remove("", *)          = ""
     * StringUtils.remove(*, null)        = *
     * StringUtils.remove(*, "")          = *
     * StringUtils.remove("queued", "ue") = "qd"
     * StringUtils.remove("queued", "zz") = "queued"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @param remove  the String to search for and remove, may be null
     * @return the substring with the string removed if found,
     *  <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String remove(String str, String remove){
		return StringUtils.remove(str, remove);
	}
	
	/**
	 * 将text中的searchString字符串替换成replacement字符串
     * <p>Replaces all occurrences of a String within another String.</p>
     *
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     *
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("any", null, *)    = "any"
     * StringUtils.replace("any", *, null)    = "any"
     * StringUtils.replace("any", "", *)      = "any"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "b"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @see #replace(String text, String searchString, String replacement, int max)
     * @param text  text to search and replace in, may be null
     * @param searchString  the String to search for, may be null
     * @param replacement  the String to replace it with, may be null
     * @return the text with any replacements processed,
     *  <code>null</code> if null String input
     *  
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String replace(String text, String searchString, String replacement){
		return StringUtils.replace(text, searchString, replacement);
	}
	
	/**
     *	反转字符串
     * <p>A <code>null</code> String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse("")    = ""
     * StringUtils.reverse("bat") = "tab"
     * </pre>
     *
     * @param str  the String to reverse, may be null
     * @return the reversed String, <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String reverse(String str){
		return StringUtils.reverse(str);
	}
	
	/**
	 * 分割字符串  以" "进行分割
     * <p>Splits the provided text into an array, using whitespace as the
     * separator.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>The separator is not included in the returned String array.
     * Adjacent separators are treated as one separator.
     * For more control over the split use the StrTokenizer class.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.split(null)       = null
     * StringUtils.split("")         = []
     * StringUtils.split("abc def")  = ["abc", "def"]
     * StringUtils.split("abc  def") = ["abc", "def"]
     * StringUtils.split(" abc ")    = ["abc"]
     * </pre>
     *
     * @param str  the String to parse, may be null
     * @return an array of parsed Strings, <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String [] split(String str){
		return StringUtils.split(str);
	}
	
	/**
	 * 按照指定字符串separatorChars进行分割str
     * <p>Splits the provided text into an array, separators specified.
     * This is an alternative to using StringTokenizer.</p>
     *
     * <p>The separator is not included in the returned String array.
     * Adjacent separators are treated as one separator.
     * For more control over the split use the StrTokenizer class.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> separatorChars splits on whitespace.</p>
     *
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str  the String to parse, may be null
     * @param separatorChars  the characters used as the delimiters,
     *  <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String [] split(String str, String separatorChars){
		return StringUtils.split(str, separatorChars);
	}
	
	/**
	 * 按照字符类型分割字符串
     * <p>Splits a String by Character type as returned by
     * <code>java.lang.Character.getType(char)</code>. Groups of contiguous
     * characters of the same type are returned as complete tokens. 
     * <pre>
     * StringUtils.splitByCharacterType(null)         = null
     * StringUtils.splitByCharacterType("")           = []
     * StringUtils.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterType("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
     * StringUtils.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
     * StringUtils.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
     * </pre>
     * @param str the String to split, may be <code>null</code>
     * @return an array of parsed Strings, <code>null</code> if null String input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String [] splitByCharacterType(String str){
		return StringUtils.splitByCharacterType(str);
	}
	
	/**
	 * 判断str是否以prefix为前缀
     * <p>Check if a String starts with a specified prefix.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.startsWith(null, null)      = true
     * StringUtils.startsWith(null, "abc")     = false
     * StringUtils.startsWith("abcdef", null)  = false
     * StringUtils.startsWith("abcdef", "abc") = true
     * StringUtils.startsWith("ABCDEF", "abc") = false
     * </pre>
     *
     * @see java.lang.String#startsWith(String)
     * @param str  the String to check, may be null
     * @param prefix the prefix to find, may be null
     * @return <code>true</code> if the String starts with the prefix, case sensitive, or
     *  both <code>null</code>
     *  
	 * @author daiqi
	 * @date 2017年12月5日 下午9:16:46
     */
	public static boolean startsWith(String str, String prefix){
		return StringUtils.startsWith(str, prefix);
	}
	
	/**
	 * 判断str是否以prefix为前缀(忽略大小写)
     * <p>Case insensitive check if a String starts with a specified prefix.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.startsWithIgnoreCase(null, null)      = true
     * StringUtils.startsWithIgnoreCase(null, "abc")     = false
     * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
     * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
     * </pre>
     *
     * @see java.lang.String#startsWith(String)
     * @param str  the String to check, may be null
     * @param prefix the prefix to find, may be null
     * @return <code>true</code> if the String starts with the prefix, case insensitive, or
     *  both <code>null</code>
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午9:16:46
     */
	public static boolean startsWithIgnoreCase(String str, String prefix){
		return StringUtils.startsWithIgnoreCase(str, prefix);
	}
	
	/**
	 * 截取字符串
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>A <code>null</code> String will return <code>null</code>.
     * An empty ("") String will return "".</p>
     *
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String input
     */
	public static String substring(String str, int start){
		return StringUtils.substring(str, start);
	}
	
	/**
	 * 截取字符串
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>A <code>null</code> String will return <code>null</code>.
     * An empty ("") String will return "".</p>
     *
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String input
     */
	public static String substring(String str, int start, int end){
		return StringUtils.substring(str, start, end);
	}
	
	/**
	 * 去除首位空格
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String returning an empty String ("") if the String
     * is empty ("") after the trim or if it is <code>null</code>.
     *
     * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if <code>null</code> input
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:21:54
     */
	public static String trimToEmpty(String str){
		return StringUtils.trimToEmpty(str);
	}
	
	/**
	 * 将str首字符小写
     * <p>Uncapitalizes a String changing the first letter to title case as
     * per {@link Character#toLowerCase(char)}. No other letters are changed.</p>
     *
     * <p>For a word based algorithm, see {@link WordUtils#uncapitalize(String)}.
     * A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize("")    = ""
     * StringUtils.uncapitalize("Cat") = "cat"
     * StringUtils.uncapitalize("CAT") = "cAT"
     * </pre>
     *
     * @param str  the String to uncapitalize, may be null
     * @return the uncapitalized String, <code>null</code> if null String input
     * @see WordUtils#uncapitalize(String)
     * @see #capitalize(String)
     * 
     * @author daiqi
	 * @date 2017年12月5日 下午8:21:54
     */
	public static String uncapitalize(String str){
		return StringUtils.uncapitalize(str);
	}
	
	 /**
     * <p>Converts a String to upper case as per {@link String#toUpperCase()}.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.upperCase(null)  = null
     * StringUtils.upperCase("")    = ""
     * StringUtils.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str  the String to upper case, may be null
     * @return the upper cased String, <code>null</code> if null String input
	 * @author daiqi
	 * @date 2017年12月5日 下午8:08:37
     */
	public static String upperCase(String str){
		return StringUtils.upperCase(str);
	}
	
	/**
	 * 
	 * <p>
	 * 替换下划线并且将下划线后一个字母大写
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param str
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月26日 上午11:01:53
	 */
	public static String replaceUnderLineAndUpperCase(String str) {
		if (DqStringUtils.isEmpty(str)) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(lowerCase(str));
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		return sb.toString().replaceAll("_", "");
	}
	/**
	 * 
	 * <p>
	 * 获取删除下划线的字符串简称
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param needRmLineStr
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月30日 上午9:52:34
	 */
	public static String getRmUnderLineSimpleStr(String needRmLineStr){
		if (isEmpty(needRmLineStr)) {
			return null;
		}
		String[] tempStrArr = needRmLineStr.split(DqSymbol.UNDER_LINE);
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		for (String str : tempStrArr) {
			String strNotTrim = DqStringUtils.trimToEmpty(str);
			if (strNotTrim.length() > 0) {
				sb.append(strNotTrim.charAt(0));
			}
		}
		return sb.toString();
	}
}
