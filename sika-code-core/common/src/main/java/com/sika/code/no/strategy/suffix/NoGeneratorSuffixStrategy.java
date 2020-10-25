package com.sika.code.no.strategy.suffix;

import com.sika.code.common.string.util.StringUtil;
import com.sika.code.no.strategy.NoGeneratorStrategy;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 后缀生成策略
 *
 * @author daiqi
 * @create 2019-06-30 21:35
 */
@Setter
@Accessors(chain = true)
public abstract class NoGeneratorSuffixStrategy implements NoGeneratorStrategy {
    protected static final int TWN = 10;
    protected static final String ZERO_STR = "0";

    @Override
    public String generateStr(int count) {
        return null;
    }

    /**
     * <p>
     * 生成后缀
     * </p>
     *
     * @param excludeNoSuffix
     * @param count
     * @return java.lang.String
     * @author daiqi
     * @date 2019/7/1 22:03
     */
    public String generateStr(String excludeNoSuffix, int count) {
        String suffix = doGenerateStr(excludeNoSuffix, count);
        int suffixLength = suffix.length();
        StringBuilder noSuffixBuilder = StringUtil.newStringBuilder(count);
        if (suffixLength < count) {
            int difference = count - suffixLength;
            for (int i = 0; i < difference; ++i) {
                noSuffixBuilder.append(ZERO_STR);
            }
        }
        noSuffixBuilder.append(suffix);
        return noSuffixBuilder.toString();
    }

    protected abstract String doGenerateStr(String excludeNoSuffix, int count);
}
