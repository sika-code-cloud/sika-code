package com.easy.cloud.no.factory;

import com.easy.cloud.basic.pojo.domain.BaseDomain;
import com.easy.cloud.common.string.constant.StringConstant;
import com.easy.cloud.common.workspace.properties.WorkspaceProperties;
import com.easy.cloud.no.generator.NoGenerator;
import com.easy.cloud.no.strategy.format.NoGeneratorTimeFormatStrategy;
import com.easy.cloud.no.strategy.suffix.NoGeneratorOrderSuffixStrategy;
import com.easy.cloud.no.strategy.suffix.NoGeneratorRandomSuffixStrategy;

/**
 * 编号工厂类
 *
 * @author daiqi
 * @create 2019-07-02 23:27
 */
public class NoFactory implements BaseDomain {

    /**
     * <p>
     * 创建毫秒和随机的编号生产者
     * </p>
     *
     * @param
     * @return com.easy.cloud.no.generator.NoGenerator
     * @author daiqi
     * @date 2019/7/2 23:36
     */
    public static NoGenerator createMillisecondRandom() {
        return new NoGenerator()
                .setFormatStrategy(NoGeneratorTimeFormatStrategy.MILLISECOND)
                .setSuffixStrategy(new NoGeneratorRandomSuffixStrategy());
    }

    /**
     * <p>
     * 创建秒和有序的编号生产者
     * </p>
     *
     * @return com.easy.cloud.no.generator.NoGenerator
     * @author daiqi
     * @date 2019/7/2 23:36
     */
    public static NoGenerator createSecondShortOrder() {
        return createSecondShortOrder(0);
    }


    /**
     * <p>
     * 创建秒和有序的编号生产者
     * </p>
     *
     * @return com.easy.cloud.no.generator.NoGenerator
     * @author daiqi
     * @date 2019/7/2 23:36
     */
    public static NoGenerator createSecondShortOrder(WorkspaceProperties workspaceProperties) {
        return createSecondShortOrder(0)
                .setWorkspaceProperties(workspaceProperties)
                .setDelimiter(StringConstant.Symbol.LINE_THROUGH)
                .build();
    }


    /**
     * <p>
     * 创建秒和有序的编号生产者
     * </p>
     *
     * @param init : 初始值
     * @return com.easy.cloud.no.generator.NoGenerator
     * @author daiqi
     * @date 2019/7/2 23:36
     */
    public static NoGenerator createSecondShortOrder(long init) {
        return new NoGenerator()
                .setFormatStrategy(NoGeneratorTimeFormatStrategy.SECOND_SHORT_YEAR)
                .setSuffixStrategy(new NoGeneratorOrderSuffixStrategy(init));
    }

}
