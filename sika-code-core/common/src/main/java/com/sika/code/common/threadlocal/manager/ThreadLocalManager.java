package com.sika.code.common.threadlocal.manager;

import cn.hutool.core.util.BooleanUtil;
import com.google.common.collect.Lists;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.threadlocal.constant.ThreadLocalOperateType;
import com.sika.code.common.threadlocal.customer.InheritableThreadLocalCustomer;
import com.sika.code.common.threadlocal.customer.ThreadLocalCustomer;

import java.util.List;

/**
 * ThreadLocal管理类，若无特殊需求使用该管理类提供的ThreadLocal和InheritableThreadLocalCustomer对象即可
 *
 * @author daiqi
 * @create 2019-06-22 23:56
 */
public class ThreadLocalManager {

    private static ThreadLocalCustomer threadLocal = new ThreadLocalCustomer();

    private static InheritableThreadLocalCustomer inheritableThreadLocal = new InheritableThreadLocalCustomer();

    private static final List<ThreadLocal> THREAD_LOCAL_CACHE_LIST = Lists.newArrayList();
    /**
     * 手动清洗名称
     */
    private static final String MANUAL_CLEAN_NAME = "sika.code.thread.local.manual.clean";

    static {
        init();
    }

    /**
     * <p>
     * 根据key获取保存在ThreadLocal中的值
     * </p>
     *
     * @param key ： 键
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/6/23 8:59
     */
    public static Object get(String key) {
        return getThreadLocal(key);
    }

    /**
     * <p>
     * 根据key获取保存在InheritableThreadLocal中的值
     * </p>
     *
     * @param key ： 键
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/6/23 8:59
     */
    public static Object get(String key, ThreadLocalOperateType threadLocalOperateType) {
        switch (threadLocalOperateType) {
            case THREAD_LOCAL:
                return getThreadLocal(key);
            case INHERITABLE:
                return getInheritable(key);
            default:
                return getThreadLocalAndInheritable(key);
        }
    }

    /**
     * <p>
     * 将key和对象放入ThreadLocal对象的map中
     * </p>
     * `
     *
     * @param key   : 键
     * @param value : 值
     * @return void
     * @author daiqi
     * @date 2019/6/23 9:00
     */
    public static void set(String key, Object value) {
        setThreadLocal(key, value);
    }

    /**
     * <p>
     * 将key和value
     * </p>
     *
     * @param key                    : 键
     * @param value                  : 值
     * @param threadLocalOperateType : 操作类型
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/6/23 16:34
     */
    public static Object set(String key, Object value, ThreadLocalOperateType threadLocalOperateType) {
        switch (threadLocalOperateType) {
            case THREAD_LOCAL:
                return setThreadLocal(key, value);
            case INHERITABLE:
                return setInheritable(key, value);
            default:
                return setThreadLocalAndInheritable(key, value);
        }
    }

    /**
     * <p>
     * 清除ThreadLocal
     * </p>
     *
     * @param
     * @return void
     * @author daiqi
     * @date 2019/6/23 16:53
     */
    public static void remove() {
        removeThreadLocal();
    }

    /**
     * <p>
     * 清除ThreadLocal
     * </p>
     *
     * @return void
     * @author daiqi
     * @date 2019/6/23 8:59
     */
    public static void removeThreadLocal() {
        for (ThreadLocal threadLocal : THREAD_LOCAL_CACHE_LIST) {
            if (threadLocal instanceof InheritableThreadLocal) {
                continue;
            }
            threadLocal.remove();
        }
    }

    /**
     * 清理InheritableThreadLocal
     */
    public static void removeInheritable() {
        for (ThreadLocal threadLocal : THREAD_LOCAL_CACHE_LIST) {
            if (threadLocal instanceof InheritableThreadLocal) {
                inheritableThreadLocal.remove();
            }
        }
    }

    /**
     * 清理所有的ThreadLocal
     */
    public static void removeAll() {
        for (ThreadLocal threadLocal : THREAD_LOCAL_CACHE_LIST) {
            threadLocal.remove();
        }
    }

    /**
     * 清理所有的ThreadLocal
     */
    public static void removeAll(Object key) {
        // 移除key
        removeThreadLocal(key);
        // 移除key
        removeInheritable(key);
    }

    /**
     * 清理ThreadLocal的Key
     */
    public static void removeThreadLocal(Object key) {
        // 移除key
        threadLocal.get().remove(key);
    }

    /**
     * 清理InheritableThreadLocal的Key
     */
    public static void removeInheritable(Object key) {
        // 移除key
        inheritableThreadLocal.get().remove(key);
    }

    public static Object getThreadLocal(String key) {
        return threadLocal.get().get(key);
    }

    public static Object getInheritable(String key) {
        return inheritableThreadLocal.get().get(key);
    }

    public static Object getThreadLocalAndInheritable(String key) {
        Object obj = getThreadLocal(key);
        if (BaseUtil.isNull(obj)) {
            obj = getInheritable(key);
        }
        return obj;
    }

    /**
     * 将key和对象放入ThreadLocal和InheritableThreadLocal对象的map中
     * </p>
     *
     * @param key   : 键
     * @param value : 值
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/6/23 16:36
     */
    public static Object setThreadLocalAndInheritable(String key, Object value) {
        setThreadLocal(key, value);
        setInheritable(key, value);
        return value;
    }

    /**
     * <p>
     * 将key和对象放入ThreadLocal对象的map中
     * </p>
     *
     * @param key   ：键
     * @param value ：存储的对象
     * @return void
     * @author daiqi
     * @date 2019/6/23 9:00
     */
    public static Object setThreadLocal(String key, Object value) {
        return threadLocal.get().put(key, value);
    }

    /**
     * <p>
     * 将key和对象放入InheritableThreadLocal对象的map中
     * </p>
     *
     * @param key    ：键
     * @param object ：存储的对象
     * @return void
     * @author daiqi
     * @date 2019/6/23 9:00
     */
    public static Object setInheritable(String key, Object object) {
        return inheritableThreadLocal.get().put(key, object);
    }

    /**
     * <p>
     * 设置清理为手动清理，若manualClean为true则必须手动调用removeThreadLocal接口来进行清理
     * </p>
     *
     * @param manualClean : 手动清理
     * @return void
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static void setManualCleanToThreadLocal(Boolean manualClean) {
        setThreadLocal(MANUAL_CLEAN_NAME, manualClean);
    }

    /**
     * <p>
     * 设置清理为手动清理，若manualClean为true则必须手动调用removeInheritable接口来进行清理
     * </p>
     *
     * @param manualClean : 手动清理
     * @return void
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static void setManualCleanToInheritable(Boolean manualClean) {
        setThreadLocal(MANUAL_CLEAN_NAME, manualClean);
    }

    /**
     * <p>
     * 设置清理为手动清理，若manualClean为true则必须手动调用removeAll接口来进行清理
     * </p>
     *
     * @param manualClean : 手动清理
     * @return void
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static void setManualCleanToAll(Boolean manualClean) {
        setThreadLocalAndInheritable(MANUAL_CLEAN_NAME, manualClean);
    }

    /**
     * 初始化
     */
    private static final void init() {
        add(threadLocal).add(inheritableThreadLocal);
    }

    /**
     * <p>
     * 将自定义的ThreadLocal增加到ThreadLocal列表中，以便在请求结束后统一进行清理
     * </p>
     *
     * @param threadLocal : 自定义的ThreadLocal
     * @return java.generator.List<java.lang.ThreadLocal>
     * @author daiqi
     * @date 2019/6/24 22:56
     */
    public static synchronized List<ThreadLocal> add(ThreadLocal threadLocal) {
        Assert.verifyObjNull(threadLocal, "ThreadLocal对象");
        if (!THREAD_LOCAL_CACHE_LIST.contains(threadLocal)) {
            THREAD_LOCAL_CACHE_LIST.add(threadLocal);
        }
        return THREAD_LOCAL_CACHE_LIST;
    }

    /**
     * <p>
     * 返回true表示自动清理
     * </p>
     *
     * @param
     * @return boolean
     * @author sikadai
     * @date 2019/8/27 22:33
     */
    public static boolean isAutoCleanThreadLocal() {
        return !isManualCleanThreadLocal();
    }

    /**
     * <p>
     * 为true表示自动清理
     * </p>
     *
     * @param
     * @return boolean
     * @author sikadai
     * @date 2019/8/27 22:32
     */
    public static boolean isAutoCleanInheritable() {
        return !isManualCleanInheritable();
    }

    /**
     * <p>
     * 是需要手动清理ThreadLocal 返回true 否则返回false
     * </p>
     *
     * @return Boolean
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static boolean isManualCleanThreadLocal() {
        return BooleanUtil.isTrue((Boolean) getThreadLocal(MANUAL_CLEAN_NAME));
    }

    /**
     * <p>
     * 是需要手动清理InheritableThreadLocal 返回true 否则返回false
     * </p>
     *
     * @return Boolean
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static Boolean isManualCleanInheritable() {
        return BooleanUtil.isTrue((Boolean) getInheritable(MANUAL_CLEAN_NAME));
    }

    /**
     * <p>
     * 自动清理
     * </p>
     *
     * @param
     * @return void
     * @author sikadai
     * @date 2019/8/27 22:31
     */
    public static void removeAuto() {
        if (isAutoCleanThreadLocal()) {
            removeThreadLocal();
        }
        if (isAutoCleanInheritable()) {
            removeInheritable();
        }
    }

}
