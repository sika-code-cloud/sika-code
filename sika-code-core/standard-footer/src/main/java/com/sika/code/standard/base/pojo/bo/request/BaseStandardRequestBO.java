package com.sika.code.standard.base.pojo.bo.request;

import com.sika.code.basic.pojo.request.BaseRequestBO;
import com.sika.code.basic.pojo.response.BaseResponseBO;

/**
 * 标准框架基础请求逻辑类，所有请求逻辑处理类必须实现该接口
 *
 * @author daiqi
 * @create 2018-08-03 14:59
 */
public abstract class BaseStandardRequestBO<ResponseBO extends BaseResponseBO> implements BaseRequestBO<ResponseBO> {

    /**
     * 是否执行 --- 防止重复执行
     */
    protected boolean execute;
    /**
     * 响应业务逻辑对象
     */
    protected ResponseBO responseBO;

    /**
     * 初始化---进行初始化操作
     */
    protected abstract void init();

    /**
     * 校验---进行入参的基础校验
     */
    protected abstract void verify();

    /**
     * 构建
     */
    protected void build() {
        init();
        verify();
    }

    @Override
    public ResponseBO execute() {
        if (isExecute()) {
            return responseBO;
        }
        // 构建
        build();
        // 去执行
        responseBO = doExecute();
        execute = true;
        return responseBO;
    }

    /**  
     * <p>
     * 子类可以通过覆盖此方法来强制每次调用execute都执行
     * </p>
     * @author daiqi
     * @date 2019/6/18 22:43
     * @return boolean
     */  
    protected boolean isExecute() {
        return execute;
    }

    /**
     * <p>
     * 具体执行的抽象方法
     * </p>
     *
     * @return ResponseBO
     * @author daiqi
     * @date 2019/6/16 23:19
     */
    protected abstract ResponseBO doExecute();

}
