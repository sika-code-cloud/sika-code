package com.sika.code.standard.base.lifecycle;

/**
 * 生命周期接口
 *
 * @author daiqi
 * @create 2019-05-16 9:53
 */
public interface LifeCycle {
    /**
     * 初始化
     */
    void init();

    /**
     * 开始
     */
    void start();

    /**
     * 销毁
     */
    void destroy();

    /**
     * 停止
     */
    void stop();
}
