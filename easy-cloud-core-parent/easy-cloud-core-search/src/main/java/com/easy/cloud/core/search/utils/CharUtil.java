package com.easy.cloud.core.search.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @Title: CharUtil
 * @Description:
 * @Author tudou
 * @Date 2018/6/20 11:37
 * @Version 2.0
 */
public class CharUtil {
    public static final char[] fbsArr = {'\\', '$', '(', ')', '*', '+', '.', '[', ']', '?', '^', '{', '}', '|', '@', '#', '%', '-', '_', '—', ':', '：','&'};
    /**
     * 转义特殊的字符
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            char[] c = keyword.toCharArray();

            StringBuilder keywordBuilder = new StringBuilder();
            for (char cc : c) {
                for (char key : fbsArr) {
                    if (cc == key) {
                        keywordBuilder.append('\\');
                    }
                }
                keywordBuilder.append(cc);
            }
            keyword = keywordBuilder.toString();
        }
        return keyword;
    }
}
