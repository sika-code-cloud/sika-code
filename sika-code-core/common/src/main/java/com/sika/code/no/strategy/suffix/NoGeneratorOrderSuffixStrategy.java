package com.sika.code.no.strategy.suffix;

import lombok.Getter;

/**
 * <p>
 * 编号有序后缀生成策略
 * </p>
 *
 * @author daiqi
 * @date 2019/5/31 10:23
 * @return
 */
@Getter
public class NoGeneratorOrderSuffixStrategy extends NoGeneratorSuffixStrategy {
    /**
     * 初始值
     */
    protected volatile long init;
    /**
     * 当前的计数
     */
    protected volatile long current;
    /**
     * 最大值
     */
    protected volatile long max;
    public NoGeneratorOrderSuffixStrategy() {

    }

    public NoGeneratorOrderSuffixStrategy(long init) {
        this.init = init;
        this.current = init;
    }

    @Override
    protected String doGenerateStr(String excludeNoSuffix, int count) {
        return String.valueOf(incrementAndGet(count));
    }

    /** 原子递增 */
    protected synchronized long incrementAndGet(int count) {
        if (max == 0) {
            max = (long)Math.pow(TWN, count - 1);
        }
        if (current >= max) {
            current = init;
        }
        return ++current;
    }


}
