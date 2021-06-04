package com.sika.code.common.spring;

/**
 * springBean的scope常量类
 *
 * @author daiqi
 * @create 2019-10-06 23:15
 */
public interface SpringBeanScope {
    /** 表示在spring容器中的单例，通过spring容器获得该bean时总是返回唯一的实例 */
    String SINGLETON  = "singleton";
    /** 表示每次获得bean都会生成一个新的对象 */
    String PROTOTYPE = "prototype";
    /** 表示在一次http请求内有效（只适用于web应用） */
    String REQUEST = "request";
    /** 表示在一个用户会话内有效（只适用于web应用） */
    String SESSION = "session";
    /** 表示在全局会话内有效（只适用于web应用） */
    String GLOBAL_SESSION = "globalSession";
}
