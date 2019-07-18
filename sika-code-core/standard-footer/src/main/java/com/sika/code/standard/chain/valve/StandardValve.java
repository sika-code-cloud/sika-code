package com.sika.code.standard.chain.valve;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.chain.pipeline.Pipeline;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标准阀门类
 *
 * @author daiqi
 * @create 2019-05-16 10:08
 */
@Data
@Accessors(chain = true)
public abstract class StandardValve<Request> implements Valve<Request> {
    /**
     * 管道对象
     */
    private Pipeline pipeline;
    /**
     * 阀门名称
     */
    private String name;
    private Valve next;

    public StandardValve() {

    }

    public StandardValve(String name) {
        this.name = name;
    }

    @Override
    public Valve notify(Pipeline pipeline) {
        reBuildNext();
        return this;
    }

    @Override
    public Valve getNext() {
        return this.next;
    }

    protected synchronized void reBuildNext() {
        int index = getIndex();
        int valveSize = pipeline.getValvesSize();
        if (index < 0 || valveSize == 0 || index >= (valveSize - 1)) {
            this.next = null;
        } else {
            this.next = this.pipeline.getValve(index + 1);
        }

    }

    /**
     * <p>
     * 获取当前阀门的index
     * </p>
     *
     * @return int
     * @author daiqi
     * @date 2019/5/16 11:01
     */
    @Override
    public int getIndex() {
        return this.pipeline.getValves().indexOf(this);
    }

    @Override
    public final boolean invoke(Request request) {
        boolean invokeResult = doInvoke(request);
        if (invokeResult && BaseUtil.isNotNull(this.getNext())) {
            this.getNext().invoke(request);
        }
        return invokeResult;
    }

    /**
     * <p>
     * 执行invoke
     * </p>
     *
     * @param request : 请求的reuqest
     * @return void
     * @author daiqi
     * @date 2019/5/16 11:54
     */
    public abstract boolean doInvoke(Request request);
}
