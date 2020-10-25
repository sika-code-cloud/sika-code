package com.sika.code.standard.chain.valve;


import com.sika.code.standard.chain.pipeline.Pipeline;

/**
 * 阀门接口
 *
 * @author daiqi
 * @create 2019-05-16 9:42
 */
public interface Valve<Request> {
    /**
     * <p>
     * 阀门的名称
     * </p>
     *
     * @return java.io.Serializable
     * @author daiqi
     * @date 2019/5/16 10:12
     */
    String getName();

    /**
     * <p>
     * 设置名称
     * </p>
     *
     * @param name : 阀门名称
     * @return Valve
     * @author daiqi
     * @date 2019/5/16 10:29
     */
    Valve setName(String name);

    /**
     * <p>
     * 获取当前阀门的下标
     * </p>
     *
     * @return int
     * @author daiqi
     * @date 2019/5/16 9:59
     */
    int getIndex();

    /**
     * <p>
     * 获取下一个阀门对象
     * </p>
     *
     * @return Valve
     * @author daiqi
     * @date 2019/5/16 9:59
     */
    Valve getNext();

    /**
     * <p>
     * 获取阀门所属的管道
     * </p>
     *
     * @return Pipeline
     * @author daiqi
     * @date 2019/5/16 10:01
     */
    Pipeline getPipeline();

    /**
     * <p>
     * 设置管道类
     * </p>
     *
     * @param pipeline
     * @return Valve
     * @author daiqi
     * @date 2019/5/16 10:08
     */
    Valve setPipeline(Pipeline pipeline);

    Valve notify(Pipeline pipeline);

    /**
     * <p>
     * 执行
     * </p>
     *
     * @param request
     * @return void
     * @author daiqi
     * @date 2019/5/16 10:04
     */
    boolean invoke(Request request);
}
