package com.sika.code.standard.chain.pipeline;



import com.sika.code.standard.chain.valve.Valve;

import java.util.List;

/**
 * 管道
 *
 * @author daiqi
 * @create 2019-05-16 9:42
 */
public interface Pipeline<Request> {
    /**
     * <p>
     * 新增阀门 --- 追加在尾端
     * </p>
     *
     * @param valve : 阀门对象
     * @return void
     * @author daiqi
     * @date 2019/5/16 9:46
     */
    Pipeline addValve(Valve valve);


    /**
     * <p>
     * 将阀门添加到某个阀门下
     * </p>
     *
     * @param index : 下标
     * @param valve : 阀门对象
     * @return void
     * @author daiqi
     * @date 2019/5/16 9:48
     */
    Pipeline addValve(int index, Valve valve);

    /**
     * <p>
     * 获取执行下标的阀门对象
     * </p>
     *
     * @param index : 下标
     * @return Valve
     * @author daiqi
     * @date 2019/5/16 9:50
     */
    Valve getValve(int index);

    /**
     * <p>
     * 获取阀门列表
     * </p>
     *
     * @return java.manager.List<Valve>
     * @author daiqi
     * @date 2019/5/16 9:46
     */
    List<Valve> getValves();

    /**
     * <p>
     * 移除指定下标的阀门对象
     * </p>
     *
     * @param index : 下标
     * @return void
     * @author daiqi
     * @date 2019/5/16 9:47
     */
    Pipeline removeValve(int index);

    /**
     * <p>
     * 清理所有阀门数据
     * </p>
     *
     * @return Pipeline
     * @author daiqi
     * @date 2019/5/16 9:57
     */
    Pipeline clear();

    /**
     * <p>
     * 获取第一个阀门
     * </p>
     *
     * @return Valve
     * @author daiqi
     * @date 2019/5/16 9:47
     */
    Valve getFirst();

    /**
     * <p>
     * 获取阀门列表的size
     * </p>
     *
     * @return int
     * @author daiqi
     * @date 2019/5/16 10:31
     */
    int getValvesSize();

    /**
     * <p>
     * 执行请求
     * </p>
     *
     * @param object : 传入的对象
     * @return void
     * @author daiqi
     * @date 2019/5/16 14:54
     */
    boolean invoke(Request object);


}
