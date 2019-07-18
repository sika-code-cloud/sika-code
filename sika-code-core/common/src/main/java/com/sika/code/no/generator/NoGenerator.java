package com.sika.code.no.generator;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.workspace.properties.WorkspaceProperties;
import com.sika.code.no.strategy.format.NoGeneratorFormatStrategy;
import com.sika.code.no.strategy.format.NoGeneratorTimeFormatStrategy;
import com.sika.code.no.strategy.suffix.NoGeneratorRandomSuffixStrategy;
import com.sika.code.no.strategy.suffix.NoGeneratorSuffixStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分布式编号生成器
 *
 * @author daiqi
 * @create 2018-08-08 15:56
 */
@Data
@Accessors(chain = true)
public class NoGenerator {
    protected static final int TWN = 10;
    protected static final String ZERO = "0";
    /**
     * 工作空间属性
     */
    private WorkspaceProperties workspaceProperties;
    /**
     * 分隔符
     */
    private String delimiter;
    /**
     * 主体格式化策略
     */
    private NoGeneratorFormatStrategy formatStrategy = NoGeneratorTimeFormatStrategy.MILLISECOND;
    /**
     * 后缀策略
     */
    private NoGeneratorSuffixStrategy suffixStrategy = new NoGeneratorRandomSuffixStrategy();
    /**
     * 工作及其ID字符串
     */
    private String workerIdStr;
    /**
     * 数据中心ID字符串
     */
    private String dataCenterIdStr;


    public NoGenerator() {

    }


    public NoGenerator build() {
        buildData();
        return this;
    }

    private void buildData() {
        if (BaseUtil.isNull(workspaceProperties)) {
            return;
        }
        buildWorkerIdStr(workspaceProperties.getWorkerId());
        buildDataCenterIdStr(workspaceProperties.getDataCenterId());
    }

    public void buildWorkerIdStr(Integer workerId) {
        this.workerIdStr = formatNumber(workerId);
    }

    public void buildDataCenterIdStr(Integer dataCenterId) {
        this.dataCenterIdStr = formatNumber(dataCenterId);
    }

    private String formatNumber(Integer number) {
        if (BaseUtil.isNull(number)) {
            return null;
        }
        String formatStr;
        if (number < TWN) {
            formatStr = ZERO + number;
        } else {
            formatStr = String.valueOf(number);
        }
        return formatStr;
    }


    /**
     * <p>
     * 生成编号的核心方法
     * </p>
     * <pre>
     * 生成规则为prefix-yyyyMMdd-18位随机纯数字
     * </pre>
     *
     * @param prefix
     * @return java.lang.String
     * @author daiqi
     * @date 2018/8/8 16:12
     */
    public String generateNoCore(String prefix, int count) {
        // 根据策略获取格式化的字符串
        String bodyStr = formatStrategy.generateStr(count);
        // 生成不包括编号后缀的字符串
        String[] excludeNoSuffixs = {prefix, workerIdStr, dataCenterIdStr, bodyStr};
        String excludeNoSuffix = generateExcludeNoSuffix(excludeNoSuffixs);
        // 生成后缀
        String suffix = generatorNoSuffix(excludeNoSuffix, count);
        // 拼接后缀字符串
        return excludeNoSuffix + suffix;
    }

    private String generateExcludeNoSuffix(String... prefixs) {
        StringBuilder noBuilder = StringUtil.newStringBuilder();
        for (String prefix : prefixs) {
            if (StringUtil.isNotBlank(prefix)) {
                noBuilder.append(prefix);
                if (StringUtil.isNotBlank(delimiter)) {
                    noBuilder.append(delimiter);
                }
            }
        }
        return noBuilder.toString();
    }

    /**
     * <p>
     * 生成编号后缀
     * </p>
     *
     * @param excludeNoSuffix : 不包括编号后缀
     * @param count           : 后缀的计数
     * @return java.lang.String
     * @author daiqi
     * @date 2019/6/30 21:31
     */
    private synchronized String generatorNoSuffix(final String excludeNoSuffix, final int count) {
        // 调用策略生成后缀
        StringBuilder noBuilder = StringUtil.newStringBuilder(count);
        String noSuffix = suffixStrategy.generateStr(excludeNoSuffix, count);

        noBuilder.append(noSuffix);
        return noBuilder.toString();
    }

}
