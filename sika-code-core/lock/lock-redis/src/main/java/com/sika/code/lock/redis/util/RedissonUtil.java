package com.sika.code.lock.redis.util;

import com.google.common.collect.Lists;
import com.sika.code.common.string.constant.StringConstant;
import com.sika.code.common.string.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author daiqi
 * @create 2019-07-28 13:20
 */
public class RedissonUtil {
    public static final String REDIS_ADDRESS_START = "redis://";

    /**  
     * <p>
     * 构建address
     * </p>
     *   
     * @param address
     * @return java.lang.String
     * @author daiqi 
     * @date 2019/7/28 13:25
     */  
    public static String buildAddress(String address) {
        return address.startsWith(REDIS_ADDRESS_START) ? address : (REDIS_ADDRESS_START + address);
    }

    /**  
     * <p>
     * 将地址列表转为地址数组
     * </p>
     *   
     * @param addresses
     * @return java.lang.String[]
     * @author daiqi 
     * @date 2019/7/28 13:36
     */  
    public static String [] convertToAddressArr(String addresses) {
        if (StringUtil.isBlank(addresses)) {
            return null;
        }
        String[] nodes = StringUtil.split(addresses, StringConstant.Symbol.COMMA);
        List<String> newNodes = Lists.newArrayList();
        Arrays.stream(nodes).forEach(index -> newNodes.add(buildAddress(index)));
        return newNodes.toArray(new String[nodes.length]);
    }
}
