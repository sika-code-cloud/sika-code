package com.sika.code.no.strategy.suffix;

import cn.hutool.core.util.RandomUtil;
import com.sika.code.common.string.util.StringUtil;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 编号随机后缀生成策略
 * </p>
 *
 * @author daiqi
 * @date 2019/5/31 10:23
 * @return
 */
@Getter
public class NoGeneratorRandomSuffixStrategy extends NoGeneratorSuffixStrategy {
    /**
     * 不包括后缀的字符串和后缀列表映射map
     */
    protected static Map<String, Set<String>> strSuffixMap = Maps.newHashMap();

    public NoGeneratorRandomSuffixStrategy() {

    }

    @Override
    protected String doGenerateStr(String excludeNoSuffix, int count) {
        return generateNoSuffix(count);
    }

    protected String generateNoSuffix(int count) {
        StringBuilder noSuffixBuilder = StringUtil.newStringBuilder(count);
        for (int i = 0; i < count; ++i) {
            noSuffixBuilder.append(RandomUtil.randomInt(TWN));
        }
        return noSuffixBuilder.toString();
    }
}
