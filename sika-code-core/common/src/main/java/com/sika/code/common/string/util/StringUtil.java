package com.sika.code.common.string.util;

import cn.hutool.core.util.StrUtil;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.common.string.constant.StringConstant;

import java.util.UUID;

/**
 * 以StrUtil包为基础封装的String操作类
 *
 * @author daiqi
 * @ClassName : DqStringUtils
 * @Description : string工具类
 * @date 2017年12月5日 下午2:04:45
 */
public class StringUtil extends StrUtil {

    /**
     * The empty String <code>""</code>.
     */
    public static final String EMPTY = StringConstant.Symbol.EMPTY;
    /**
     * 分割符---英文冒号---:
     */
    public static final String COLON = StringConstant.Symbol.COLON;
    /**
     * 初始化相关String容器的容量--32
     */
    private static final int INIT_CAPACITY = 32;

    /**
     * <p>
     * 生成uuid的字符串---默认32位
     * </p>
     *
     * @return
     * @author daiqi
     * @创建时间 2018年5月31日 上午10:44:56
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * <p>
     * 使用英文冒号:分隔符来生成统一规范的key
     * </p>
     * <p>
     * <pre>
     * StringUtil.generateKeyDefault("arg1", "arg2", "arg3") = arg1:arg2:arg3
     * StringUtil.generateKeyDefault(null) = null
     * </pre>
     *
     * @param args : String : key的可变列表,不包括分隔符
     * @return
     * @author daiqi
     * @创建时间 2018年4月16日 上午9:29:38
     */
    public static String generateKeyUseColonSeparator(String... args) {
        return generateKey(StringConstant.Symbol.COLON, args);
    }

    /**
     * <p>
     * 生成统一规范key的公共方法，使用第一个参数做为分隔符
     * </p>
     * <p>
     * <pre>
     * StringUtil.generateKey("-", "arg1", "arg2", "arg3") = arg1-arg2-arg3
     * StringUtil.generateKey("", "arg1", "arg2", "arg3") = arg1:arg2:arg3
     * StringUtil.generateKey(null, "arg1", "arg2", "arg3") = arg1:arg2:arg3
     * StringUtil.generateKey(null) = null
     * StringUtil.generateKey(null, null) = null
     * </pre>
     *
     * @param separator : String : key分隔符,为null或者""使用:
     * @param args      : String : key的可变列表
     * @return 生成好的key
     * @author daiqi
     * @创建时间 2018年4月16日 上午9:26:53
     */
    public static String generateKey(final String separator, final String... args) {
        if (ArrayUtil.isEmpty(args)) {
            return null;
        }
        String separatorTemp = separator;
        if (StringUtil.isEmpty(separatorTemp)) {
            separatorTemp = StringConstant.Symbol.COLON;
        }

        StringBuilder keyStrBuild = newStringBuilder();
        int argsLength = args.length;
        for (int i = 0; i < argsLength; ++i) {
            if (isEmpty(args[i])) {
                continue;
            }
            keyStrBuild.append(args[i]);
            if (i < argsLength - 1) {
                keyStrBuild.append(separatorTemp);
            }
        }
        return keyStrBuild.toString();
    }

    /**
     * <p>创建默认capacity的StringBuilder对象</p>
     * <p>
     * <pre></pre>
     *
     * @return author daiqi
     * 创建时间  2018年1月8日 下午6:06:21
     */
    public static StringBuilder newStringBuilder() {
        return newStringBuilder(INIT_CAPACITY);
    }

    /**
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
     * <p>
     * author daiqi
     * 创建时间  2018年1月8日 下午6:04:33
     */
    public static StringBuilder newStringBuilder(final Integer initCapacity) {
        if (BaseUtil.isNull(initCapacity) || initCapacity <= 0) {
            return new StringBuilder(INIT_CAPACITY);
        }
        return new StringBuilder(initCapacity);
    }

}
