package com.easy.cloud.common.threadlocal.manager;

import cn.hutool.core.util.BooleanUtil;
import com.easy.cloud.basic.util.Assert;
import com.easy.cloud.basic.util.BaseUtil;
import com.easy.cloud.common.threadlocal.constant.ThreadLocalOperateType;
import com.easy.cloud.common.threadlocal.customer.InheritableThreadLocalCustomer;
import com.easy.cloud.common.threadlocal.customer.ThreadLocalCustomer;
import com.google.common.collect.Lists;

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
    private static final String MANUAL_CLEAN_NAME = "lxzl.thread.local.manual.clean";

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
     * <p>
     * 从ThreadLocal中获取手动清理
     * </p>
     *
     * @return Boolean
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static boolean getManualCleanFromThreadLocal() {
        return BooleanUtil.isTrue((Boolean) getThreadLocal(MANUAL_CLEAN_NAME));
    }

    /**
     * <p>
     * 从InheritableThreadLocal中获取手动清理
     * </p>
     *
     * @return Boolean
     * @author daiqi
     * @date 2019/7/10 16:14
     */
    public static Boolean getManualCleanFromInheritable() {
        return BooleanUtil.isTrue((Boolean) getInheritable(MANUAL_CLEAN_NAME));
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


}
