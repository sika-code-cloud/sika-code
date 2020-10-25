package com.sika.code.standard.chain.pipeline;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.lifecycle.LifeCycle;
import com.sika.code.standard.chain.valve.Valve;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 标准阀门实现类
 *
 * @author daiqi
 * @create 2019-05-16 9:57
 */
public class StandardPipeline<Request> implements Pipeline<Request>, LifeCycle {
    private final List<Valve> valves = Lists.newArrayList();

    @Override
    public synchronized Pipeline addValve(Valve valve) {
        this.valves.add(valve);
        buildValve(valve);
        return this;
    }

    @Override
    public synchronized Pipeline addValve(int index, Valve valve) {
        this.valves.add(index, valve);
        buildValve(valve);
        return this;
    }

    /**
     * <p>
     * 构建阀门
     * </p>
     *
     * @param valve
     * @return void
     * @author daiqi
     * @date 2019/5/16 11:21
     */
    protected void buildValve(Valve valve) {
        valve.setPipeline(this);
        notifyValves();
    }

    @Override
    public Valve getValve(int index) {
        return this.valves.get(index);
    }

    @Override
    public List<Valve> getValves() {
        return this.valves;
    }

    @Override
    public synchronized Pipeline removeValve(int index) {
        this.valves.remove(index);
        notifyValves();
        return this;
    }

    @Override
    public synchronized Pipeline clear() {
        this.valves.clear();
        return this;
    }

    @Override
    public Valve getFirst() {
        if (this.getValvesSize() == 0) {
            return null;
        }
        return this.valves.get(0);
    }

    @Override
    public boolean invoke(Request object) {
        Valve valve = getFirst();
        if (BaseUtil.isNull(valve)) {
            return false;
        }
        return valve.invoke(object);
    }

    @Override
    public int getValvesSize() {
        return this.valves.size();
    }

    @Override
    public synchronized void init() {

    }

    @Override
    public synchronized void start() {

    }

    @Override
    public synchronized void destroy() {
        this.clear();
    }

    @Override
    public synchronized void stop() {

    }
    protected void notifyValves() {
        if (this.getValvesSize() == 0) {
            return;
        }
        for (Valve valveFrom : this.getValves()) {
            valveFrom.notify(this);
        }
    }
}
